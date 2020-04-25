<template>
  <div>
    <bread></bread>
    <div>
      <iframe width="100%" style="height: 600px"
              src="http://localhost:2000/BLOG-WEB-SERVER/swagger-ui.html"
              frameborder="0">
      </iframe>
    </div>
  </div>
</template>

<script>
  import bread from "../../components/bread";

  export default {
    name: "swagger",
    components: {
      bread,
    },

  }
</script>

<style scoped>
</style>
