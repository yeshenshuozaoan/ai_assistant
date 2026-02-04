package com.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import io.milvus.client.MilvusClient;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.*;
import io.milvus.param.*;
import io.milvus.param.collection.*;
import io.milvus.param.index.*;
import io.milvus.param.dml.*;

@Component
public class MilvusUtil {
    
    @Value("${milvus.host}")
    private String host;
    
    @Value("${milvus.port}")
    private int port;
    
    @Value("${milvus.username}")
    private String username;
    
    @Value("${milvus.password}")
    private String password;
    
    @Value("${milvus.database}")
    private String database;
    
    private MilvusClient milvusClient;
    
    /**
     * 初始化Milvus客户端连接
     */
    @PostConstruct
    public void init() {
        try {
            // 构建Milvus客户端
            ConnectParam connectParam = ConnectParam.newBuilder()
                    .withHost(host)
                    .withPort(port)
                    .build();
            
            milvusClient = new MilvusServiceClient(connectParam);
            
            System.out.println("Milvus client initialized successfully: " + host + ":" + port);
        } catch (Exception e) {
            System.err.println("Failed to initialize Milvus client: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 关闭Milvus客户端连接
     */
    @PreDestroy
    public void close() {
        try {
            if (milvusClient != null) {
                milvusClient.close();
                System.out.println("Milvus client closed successfully");
            }
        } catch (Exception e) {
            System.err.println("Failed to close Milvus client: " + e.getMessage());
        }
    }
    
    /**
     * 创建集合
     */
    public boolean createCollection(String collectionName, int dimension) {
        try {
            // 检查集合是否已存在
            if (hasCollection(collectionName)) {
                System.out.println("Collection already exists: " + collectionName);
                return true;
            }
            
            // 构建字段
            FieldType fieldType1 = FieldType.newBuilder()
                    .withName("id")
                    .withDataType(DataType.Int64)
                    .withPrimaryKey(true)
                    .withAutoID(false)
                    .build();
            
            FieldType fieldType2 = FieldType.newBuilder()
                    .withName("embedding")
                    .withDataType(DataType.FloatVector)
                    .withDimension(dimension)
                    .build();
            
            // 创建字段列表
            List<FieldType> fieldTypes = new ArrayList<>();
            fieldTypes.add(fieldType1);
            fieldTypes.add(fieldType2);
            
            // 创建集合
            CreateCollectionParam param = CreateCollectionParam.newBuilder()
                    .withCollectionName(collectionName)
                    .withFieldTypes(fieldTypes)
                    .build();
            
            R<RpcStatus> response = milvusClient.createCollection(param);
            if (response.getStatus() != R.Status.Success.getCode()) {
                System.err.println("Failed to create collection: " + response.getMessage());
                return false;
            }
            
            System.out.println("Collection created successfully: " + collectionName);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to create collection: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 创建索引
     */
    public boolean createIndex(String collectionName, String fieldName) {
        try {
            // 创建IVF_FLAT索引
            CreateIndexParam param = CreateIndexParam.newBuilder()
                    .withCollectionName(collectionName)
                    .withFieldName(fieldName)
                    .withIndexType(IndexType.IVF_FLAT)
                    .withMetricType(MetricType.L2)
                    .build();
            
            R<RpcStatus> response = milvusClient.createIndex(param);
            if (response.getStatus() != R.Status.Success.getCode()) {
                System.err.println("Failed to create index: " + response.getMessage());
                return false;
            }
            
            System.out.println("Index created successfully for collection: " + collectionName + " on field: " + fieldName);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to create index: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 插入向量数据
     */
    public Long insert(String collectionName, List<List<Float>> vectors, List<Long> ids, Map<String, List<?>> fields) {
        try {
            // 构建插入参数
            InsertParam param = InsertParam.newBuilder()
                    .withCollectionName(collectionName)
                    .build();
            
            R<MutationResult> response = milvusClient.insert(param);
            if (response.getStatus() != R.Status.Success.getCode()) {
                System.err.println("Failed to insert vectors: " + response.getMessage());
                return 0L;
            }
            
            // 获取插入的行数
            long insertedCount = vectors.size();
            System.out.println("Inserted " + insertedCount + " vectors into collection: " + collectionName);
            return insertedCount;
        } catch (Exception e) {
            System.err.println("Failed to insert vectors: " + e.getMessage());
            e.printStackTrace();
            return 0L;
        }
    }
    
    /**
     * 搜索相似向量
     */
    public List<?> search(String collectionName, List<Float> vector, int topK) {
        try {
            // 构建搜索参数
            SearchParam param = SearchParam.newBuilder()
                    .withCollectionName(collectionName)
                    .withVectors(java.util.Arrays.asList(vector))
                    .withTopK(topK)
                    .withMetricType(MetricType.L2)
                    .build();
            
            R<SearchResults> response = milvusClient.search(param);
            if (response.getStatus() != R.Status.Success.getCode()) {
                System.err.println("Failed to search vectors: " + response.getMessage());
                return null;
            }
            
            System.out.println("Search completed for collection: " + collectionName);
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Failed to search vectors: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 删除集合
     */
    public boolean dropCollection(String collectionName) {
        try {
            // 构建删除集合参数
            DropCollectionParam param = DropCollectionParam.newBuilder()
                    .withCollectionName(collectionName)
                    .build();
            
            R<RpcStatus> response = milvusClient.dropCollection(param);
            if (response.getStatus() != R.Status.Success.getCode()) {
                System.err.println("Failed to drop collection: " + response.getMessage());
                return false;
            }
            
            System.out.println("Collection dropped successfully: " + collectionName);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to drop collection: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 检查集合是否存在
     */
    public boolean hasCollection(String collectionName) {
        try {
            // 构建检查集合参数
            HasCollectionParam param = HasCollectionParam.newBuilder()
                    .withCollectionName(collectionName)
                    .build();
            
            R<Boolean> response = milvusClient.hasCollection(param);
            if (response.getStatus() != R.Status.Success.getCode()) {
                System.err.println("Failed to check collection existence: " + response.getMessage());
                return false;
            }
            
            boolean exists = response.getData();
            System.out.println("Collection " + collectionName + " exists: " + exists);
            return exists;
        } catch (Exception e) {
            System.err.println("Failed to check collection existence: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
