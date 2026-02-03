<template>
  <div class="home">
    <div class="container">
      <h1>欢迎来到Vue3前端项目</h1>
      <p>这是一个使用Vue3 + Vite构建的前端项目</p>
      <div class="card">
        <h2>项目特点</h2>
        <ul>
          <li>使用Vue3 Composition API</li>
          <li>集成Vue Router</li>
          <li>响应式设计</li>
          <li>模块化结构</li>
        </ul>
      </div>
      <router-link to="/about" class="btn btn-primary">了解更多</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.home {
  padding: 40px 0;
}

.home h1 {
  font-size: 32px;
  margin-bottom: 20px;
  color: #303133;
}

.home p {
  font-size: 18px;
  margin-bottom: 30px;
  color: #606266;
}

.card {
  margin: 30px 0;
}

.card h2 {
  font-size: 24px;
  margin-bottom: 16px;
  color: #303133;
}

.card ul {
  list-style: none;
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
