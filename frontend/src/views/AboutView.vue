<template>
  <div class="about">
    <div class="container">
      <h1>关于本项目</h1>
      <div class="card">
        <h2>项目信息</h2>
        <p>这是一个基于Vue3 + Vite构建的前端项目，旨在展示Vue3的核心特性和最佳实践。</p>
        <h3>技术栈</h3>
        <ul>
          <li>Vue 3</li>
          <li>Vite</li>
          <li>Vue Router</li>
        </ul>
        <h3>项目结构</h3>
        <ul>
          <li>src/components - 组件目录</li>
          <li>src/views - 页面视图目录</li>
          <li>src/router - 路由配置目录</li>
          <li>src/styles - 样式文件目录</li>
          <li>src/api - API请求目录</li>
          <li>src/utils - 工具函数目录</li>
        </ul>
      </div>
      <router-link to="/" class="btn btn-primary">返回首页</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutView'
}
</script>

<style scoped>
.about {
  padding: 40px 0;
}

.about h1 {
  font-size: 32px;
  margin-bottom: 20px;
  color: #303133;
}

.card {
  margin: 30px 0;
}

.card h2 {
  font-size: 24px;
  margin-bottom: 16px;
  color: #303133;
}

.card h3 {
  font-size: 18px;
  margin: 20px 0 10px 0;
  color: #409eff;
}

.card p {
  margin-bottom: 20px;
  color: #606266;
  line-height: 1.6;
}

.card ul {
  list-style: none;
  margin-bottom: 20px;
}

.card li {
  padding: 8px 0;
  color: #606266;
  border-bottom: 1px solid #f0f0f0;
}

.card li:last-child {
  border-bottom: none;
}

.btn {
  margin-top: 20px;
}
</style>
