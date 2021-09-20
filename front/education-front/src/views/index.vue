<template>
  <div class="index">
    <el-container>
      <el-header>
        <span>简易教务管理系统</span>
        <span class="nickname">{{ nickname }}</span>
        <el-button type="primary" class="logout" @click="logout()">退出登录</el-button>
      </el-header>
      <el-container>
        <el-aside width="180px">
          <el-row class="tac">
            <el-col>
              <el-menu
                  :default-active="active"
                  class="el-menu-vertical-demo"
                  background-color="#545c64"
                  text-color="#fff"
                  router
                  active-text-color="#ffd04b"
              >
                <el-submenu :index="item.menu.path" v-for="item in menuList"
                            :key="item.menu.permissionId">
                  <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>{{ item.menu.permissionName }}</span>
                  </template>
                    <el-menu-item :index="it.path" v-for="it in item.subMenu"
                                  :key="it.permissionId"
                                  @click="saveActive(it.path)">
                      {{ it.permissionName }}
                    </el-menu-item>
                </el-submenu>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "index",

  data() {
    return {
      nickname: '姜尚',
      menuList: [],
      active: ''
    }
  },
  methods: {
    getMenuList() {
      var username
      if (this.$store.getters.getUser == null || this.$store.getters.getUser == '') {
        username = '';
      } else {
        username = this.$store.getters.getUser.username
      }
      this.$axios.get(`/user/getMenuList?username=${username}`,
          {
            headers: {
              "Authorization": this.$store.getters.getToken
            }
          }).then(Response => {
        console.log(Response.data.data)
        this.menuList = Response.data.data
      })
    },
    saveActive(val) {
      this.active = val;
      localStorage.setItem("active" , val);
    },

    logout() {
      this.$confirm('确认退出登录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '退出登录成功!'
        });
        this.$store.commit("REMOVE_INFO")
        this.$router.push("/login")
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    }
  },
    created() {

      this.getMenuList();
      this.active = localStorage.getItem("active")
      this.nickname =this.$store.getters.getUser.nickname

    }
}
</script>

<style scoped>
    .logout{
      position: absolute;
      right: 10px;
      margin-top: 10px;
    }
    .nickname{
      position: absolute;
      right: 150px;
      color: #E9EEF3;
      cursor: pointer;
    }

    .index {
      padding: 0px;
      margin: 0px;
      height: 100%;
    }

    .el-container {
      height: 100%;
    }

    .el-menu {
      border-right: 0;
      text-align: left;
    }

    .el-header, .el-footer {
      background-color: #B3C0D1;
      color: #333;
      text-align: center;
      line-height: 60px;
    }

    .el-aside {
      background-color: #D3DCE6;
      color: #333;
      text-align: center;
      line-height: 200px;
    }

    .el-main {
      background-color: #E9EEF3;
      color: #333;
      text-align: center;

    }

</style>