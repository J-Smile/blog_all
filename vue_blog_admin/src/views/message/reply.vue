<template>
  <div>
    <bread></bread>

    <el-table
      v-loading="loading"
      :data="data"
      style="width: 100%">
      <el-table-column
        label="序号"
        type="index"
        width="220">
      </el-table-column>
      <el-table-column
        label="头像"
        width="220">
        <template slot-scope="scope">
          <el-avatar v-if="scope.row.user.avatar" :src="scope.row.user.avatar"></el-avatar>
          <el-avatar v-else src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
        prop="user.nickname"
        label="姓名"
        width="220">
      </el-table-column>
      <el-table-column
        prop="text"
        width="220"
        label="内容">
      </el-table-column>
      <el-table-column
        prop="blogName"
        width="220"
        label="博客标题">
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button type="danger" @click="deleteComment(scope.row.id)" round>删除</el-button>
        </template>
      </el-table-column>
    </el-table>


    <pagination v-bind:child-msg="pageparm" @callFather="callFather"></pagination>

  </div>
</template>

<script>
  import bread from "../../components/bread";
  import {del, name, find_page} from "../../api/api";
  import pagination from "../../components/Pagination";
  import {notification_error, notification_success} from "../../utils/notification";

  export default {
    components: {
      bread,
      pagination
    },
    data() {
      return {
        formInline: {
          pageNum: 1,
          pageSize: 7,
        },
        pageparm: {
          currentPage: 1,
          pageSize: 7,
          total: 10
        },
        loading: true,
        data: []
      }
    },
    methods: {
      deleteComment(id) {
        del("comment", id).then(res => {
          notification_success('删除成功')
          this.init();
        }).catch((err) => {
            notification_error(err)
          }
        )

      },
      callFather(parm) {
        this.formInline.pageNum = parm.currentPage;
        this.formInline.pageSize = parm.pageSize;
        this.init();
        console.log(123)
      },
      async init() {
        let {data} = await find_page(name.reply, this.formInline.pageNum, this.formInline.pageSize);

        this.data = data.data.list;
        this.pageparm.currentPage = this.formInline.pageNum;
        this.pageparm.pageSize = this.formInline.pageSize;
        this.pageparm.total = data.data.total
        this.loading = false;
      }
    },
    created() {
      this.init();
    }

  }
</script>

<style scoped>

</style>
