<template>
  <div style="background-color: #545c64">
    <el-menu class="el-menu-demo" id="head" mode="horizontal"
             background-color="#545c64"
             text-color="#fff" style="">
      <el-menu-item index="1">
        <div style="padding-right: 60%;font-weight: bolder;font-size: 30px">Coder博客</div>
      </el-menu-item>
      <span style="padding-left: 50%"></span>
      <el-menu-item index="2"><a href="/">首页</a></el-menu-item>
      <el-menu-item index="3"><a href="/about">关于我</a></el-menu-item>
      <el-menu-item index="4"><a href="/messageBoard">留言板</a></el-menu-item>
      <el-submenu index="5">
        <template slot="title">
          <el-avatar :src="image_url"></el-avatar>
        </template>
        <div v-if="user.avatar==''"><a href="/login">
          <el-menu-item>登录</el-menu-item>
        </a></div>
        <div v-else>
          <el-menu-item index="5-1" @click="user_center()">个人中心</el-menu-item>
          <el-menu-item index="5-2" @click="out()">退出登录</el-menu-item>
        </div>
      </el-submenu>
    </el-menu>

    <!--dialog-->
    <el-drawer
      :visible.sync="drawer"
      :with-header="false">
      <el-tabs type="border-card" tab-position="left" style="width: 100%;height: 100%">
        <el-tab-pane icon="el-icon-user" label="个人中心">
          <el-form :inline="true" :model="user" class="demo-form-inline">
            <el-form-item label="头像">
              <el-avatar :size="50" :src="user.avatar"></el-avatar>
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="user.nickname" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio v-model="user.gender" :label="0" border>男</el-radio>
              <el-radio v-model="user.gender" :label="1" border>女</el-radio>
            </el-form-item>
            <el-form-item label="生日">
              <el-date-picker
                v-model="user.birthday"
                type="date"
                format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="user.email" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="QQ">
              <el-input v-model="user.qq" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="职业">
              <el-input v-model="user.work" placeholder="请输入内容"></el-input>
            </el-form-item>
            <el-form-item label="简介">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="user.userDesc">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button align="center" type="primary" @click="editUser()">更新</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="我的评论">
          <el-timeline v-if="comments.length>0" >
            <el-timeline-item v-for="(comment,i) in comments" :key="i" :timestamp="comment.time" placement="top">
              <el-card>
                <p>
                  {{comment.text}}
                  <a v-if="comment.flog==0" href="/messageBoard">
                    <el-tag>留言</el-tag>
                  </a>
                  <a v-if="comment.flog==1" :href="'/show/'+comment.bid">
                    <el-tag type="success">评论</el-tag>
                  </a>
                </p>
              </el-card>
            </el-timeline-item>
          </el-timeline>

          <el-timeline-item v-else placement="top">
            <el-card>
              <h4>没有评论呢</h4>
            </el-card>
          </el-timeline-item>
        </el-tab-pane>

        <el-tab-pane label="我的回复">
          <el-timeline v-if="replies.length>0">
            <el-timeline-item v-for="(reply,i) in replies" :key="i" :timestamp="reply.time" placement="top">
              <el-card>
                <p>
                  {{reply.text}}
                  <a v-if="reply.flog==1" :href="'/show/'+reply.bid">
                    <el-tag type="success">回复</el-tag>
                  </a>
                </p>
              </el-card>
            </el-timeline-item>
          </el-timeline>

          <el-timeline-item v-else placement="top">
            <el-card>
              <h4>没有任何回复呢</h4>
            </el-card>
          </el-timeline-item>

        </el-tab-pane>

        <el-tab-pane label="我的反馈">
          <el-collapse>
            <el-collapse-item title="反馈须知" name="1">
              <div> 如果您对本站有什么想法，可以在这里进行反馈</div>
              <div> 或者加入我们的QQ群进行交流</div>
            </el-collapse-item>
          </el-collapse>

          <br>

          <el-timeline v-if="feedbacks.length>0">
            <el-timeline-item v-for="(feedback,i) in feedbacks":key="i" :timestamp="feedback.time" placement="top">
              <el-card>
                <p>
                  {{feedback.text}}
                </p>
              </el-card>
            </el-timeline-item>
          </el-timeline>
          <el-timeline-item v-else placement="top">
            <el-card>
              <h4>您还没有写反馈呢</h4>
            </el-card>
          </el-timeline-item>
          <br>

          <el-form :inline="true" :model="feedback" class="demo-form-inline">
            <el-form-item label="标题">
              <el-input v-model="feedback.title" placeholder="审批人"></el-input>
            </el-form-item>
            <el-form-item label="反馈">
              <el-input v-model="feedback.text"
                        type="textarea"
                        :rows="2" placeholder="反馈"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="add_feedback()">添加反馈</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>


        <el-tab-pane label="申请友链">

          <el-collapse v-model="activeNames">

            <el-collapse-item title=" 申请须知" name="1">
              <div>请确定贵站可以稳定运营</div>
              <div>原创博客优先，技术类博客优先</div>
              <div>申请前请先添加下方博客友链</div>
              <div>欢迎各位小伙伴一起互换友链~</div>
            </el-collapse-item>
            <el-collapse-item title="博客" name="2">
              <div>网站简介：蘑菇博客 - 专注于技术分享的博客平台</div>
              <div>网站地址：</div>
            </el-collapse-item>
            <br>
            <el-form :model="friendship" status-icon :rules="rules" ref="ruleForm"
                     class="demo-ruleForm">
              <el-form-item label="网站" prop="name">
                <el-input type="password" v-model="friendship.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="简介" prop="description">
                <el-input type="password" v-model="friendship.description" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" prop="url">
                <el-input v-model="friendship.url"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
              </el-form-item>
            </el-form>

          </el-collapse>
        </el-tab-pane>
      </el-tabs>
    </el-drawer>

  </div>
</template>

<script>
  import {state, error_message, success_update} from "../utils/message";

  export default {
    data() {
      return {
        user: {
          avatar: ""
        },
        drawer: false,
        image_url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        comments: [],
        replies: [],
        feedbacks: [],
        feedback: {},
        activeNames: ['1', '2'],
        friendship: {},
        rules: {
          name: [
            {required: true, message: '请输入网站名称', trigger: 'blur'}
          ],
          description: [
            {required: true, message: '请输入简介', trigger: 'blur'}
          ],
          url: [
            {required: true, message: '请输入网站地址', trigger: 'blur'}
          ],

        }
      }
    },
    methods: {
      async submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.$post("friendship", this.friendship).then(res => {

              if (res.code == 0) {
                this.$notify({
                  title: '成功',
                  message: '添加成功',
                  type: 'success'
                });
              } else if (res.code == 1100) {
                this.$notify.error({
                  title: '错误',
                  message: '登录失效，请重新登录'
                });
                sessionStorage.removeItem("token")
                sessionStorage.removeItem("user")
              } else {
                this.$notify({
                  title: '警告',
                  message: data.msg,
                  type: 'warning'
                });
              }
            })
          } else {
            this.$notify({
              title: '警告',
              message: "请认真填写友链信息",
              type: 'warning'
            });
            return false;
          }
        });
      },
      async add_feedback() {
        const res = await this.$axios.$post("/feedback", this.feedback)
        if (res.code == 0) {
          this.$notify({
            title: '成功',
            message: '添加成功',
            type: 'success'
          });
        } else if (res.code == 1100) {
          this.$notify.error({
            title: '错误',
            message: '登录失效，请重新登录'
          });
          sessionStorage.removeItem("token")
          sessionStorage.removeItem("user")
        } else {
          this.$notify({
            title: '警告',
            message: res.msg,
            type: 'warning'
          });
        }
        this.feedback = {}
      }
      ,
      async editUser() {
        let {code} = await this.$axios.$put("/user", this.user);
        if (code != 0) {
          error_message(state.error_common);
        } else {
          sessionStorage.setItem("user", JSON.stringify(this.user))
          success_update();
        }
      }
      ,
      out() {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("user");
        window.location.reload();
        this.image_url = "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      }
      ,
      async user_center() {
        this.drawer = true;

        const res = await this.$axios.$get("/comment/nowUser")
        if (res.code == 0) {
          this.comments = res.data;
        } else if (res.code == 1100) {
          this.$notify.error({
            title: '错误',
            message: '登录失效，请重新登录'
          });
          sessionStorage.removeItem("token")
          sessionStorage.removeItem("user")
        } else {
          this.$notify({
            title: '警告',
            message: res.msg,
            type: 'warning'
          });
        }

        const res_data = await this.$axios.$get("/reply/nowUser")
        if (res_data.code == 0) {
          this.replies = res_data.data;
        } else if (res.code == 1100) {
          this.$notify.error({
            title: '错误',
            message: '登录失效，请重新登录'
          });
          sessionStorage.removeItem("token")
          sessionStorage.removeItem("user")
        } else {
          this.$notify({
            title: '警告',
            message: res.msg,
            type: 'warning'
          });
        }

        const feedback_data = await this.$axios.$get("/feedback/nowUser")
        if (feedback_data.code == 0) {
          this.feedbacks = feedback_data.data;
        } else if (feedback_data.code == 1100) {
          this.$notify.error({
            title: '错误',
            message: '登录失效，请重新登录'
          });
          sessionStorage.removeItem("token")
          sessionStorage.removeItem("user")
        } else {
          this.$notify({
            title: '警告',
            message: res.msg,
            type: 'warning'
          });
        }


      }
    }
    ,
    async mounted() {
      let user = sessionStorage.getItem("user");
      if (user != null) {
        user = JSON.parse(user);
        this.user = user;
        this.image_url = user.avatar;
      }


    }
  }
</script>

<style scoped>
  #head {
    width: 100%;
    z-index: 10;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    flex-direction: row
  }
</style>
