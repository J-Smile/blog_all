<template>
  <div class="container" style="padding-top: 5%;display: flex;flex-direction: row">
    <div class="content-wrap">
      <!--中间内容-->
      <div class="content">
        <div class="excerpt excerpt-2" v-if="blogs.length>0" v-for="blog in blogs">
          <article>
            <a class="focus" :href="url+'/show/'+blog.bid"
               :title="blog.title" target="_blank"
               draggable="false">
              <img class="thumb"
                   :data-original="blog.images"
                   :src="blog.images"
                   draggable="false" style="display: inline;">
            </a>
            <header>
              <h1>
                <a :href="url+'/show/'+blog.bid"
                   :title="blog.title" target="_blank"
                   draggable="false">{{blog.title}}</a>
              </h1>
            </header>
            <p class="meta">
              <time class="time"><i class="glyphicon glyphicon-time"></i> {{blog.createTime}}</time>
              <span class="views"><i class="glyphicon glyphicon-eye-open"></i> {{blog.clickCount}}</span>
              <a class="comment"
                 :href="url+'/show/711.html#comment'"
                 title="评论" target="_blank"
                 draggable="false"><i
                class="glyphicon glyphicon-comment"></i> {{blog.discussCount}}</a>
            </p>
          </article>
        </div>
        <div v-if="blogs.length==0">
          到底了~~~
        </div>
      </div>
    </div>

    <Right></Right>
  </div>
</template>

<script>
  import Right from "../../components/Right";
  import {sysConfig} from "../../utils/url";

  export default {
    layout: "headfoot",
    name: "_title",
    components: {
      Right
    },
    data() {
      return {
        title: "",
        blogs: [],
        url:sysConfig.WEB_URL
      }
    },
    async mounted() {
      this.title = this.$route.params.title;
      let {data} = await this.$axios.$get("search/" + this.title);
      this.blogs = data.data
      console.log(data.data)
    }
  }
</script>

<style scoped>

</style>
