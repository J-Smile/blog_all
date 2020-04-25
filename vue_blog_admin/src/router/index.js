import Vue from 'vue';
import Router from 'vue-router';
import login from '@/views/login';
import index from '@/views/index';
import Smile from "../views/index/Smile";
import Blog from "../views/blog/Blog";
import blog_add from "../views/blog/blog_add";
import Sort from "../views/blog/Sort";
import Tag from "../views/blog/Tag";
import blog_edit from "../views/blog/blog_edit";
import LunBo from "../views/website/LunBo";
import Background from "../views/website/Background";
import Author from "../views/website/Author";
import About from "../views/website/About";
import swagger_web from "../views/swagger/swagger_web";
import swagger_admin from '../views/swagger/swagger_admin'
import comment from "../views/message/comment";
import reply from "../views/message/reply";
import leavemessage from "../views/message/leavemessage";


// 启用路由
Vue.use(Router);

// 导出路由
export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    name: 'index',
    component: login,
  },
    {
      path: '/index',
      name: '首页',
      component: index,
      iconCls: 'el-icon-tickets',
      children: [
        {
          path: '/smile',
          component: Smile,
        },
        {
          path: '/blog',
          name: '博客管理-博客中心',
          component: Blog,
        },
        {
          path: '/blog_add',
          name: '博客管理-添加博客',
          component: blog_add,
        },
        {
          path: '/sort',
          name: '博客管理-分类管理',
          component: Sort,
        },
        {
          path: '/tag',
          name: '博客管理-标签管理',
          component: Tag,
        },
        {
          path: '/blog_edit/:id',
          name: '博客管理-博客修改',
          component: blog_edit,
        },

        {
          path: '/lunbo',
          name: '网站管理-轮播图',
          component: LunBo,
        },
        {
          path: '/background',
          name: '网站管理-背景图片',
          component: Background,
        },
        {
          path: '/author',
          name: '网站管理-信息中心',
          component: Author,
        },
        {
          path: '/about',
          name: '网站管理-关于我',
          component: About,
        },
        {
          path: '/swagger-web',
          name: 'swagger-web',
          component: swagger_web,
        },
        {
          path: '/swagger-admin',
          name: 'swagger-admin',
          component: swagger_admin,
        },
        {
          path: '/comment',
          name: '信息管理-评论管理',
          component: comment,
        },
        {
          path: '/leaveMessage',
          name: '信息管理-留言管理',
          component: leavemessage,
        },
        {
          path: '/reply',
          name: '信息管理-回复管理',
          component: reply,
        },

      ]
    }]
})
