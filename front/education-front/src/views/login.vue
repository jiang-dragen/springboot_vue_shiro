<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login-ruleForm">
    <el-form-item label="账号" prop="username">
      <el-input v-model="ruleForm.username"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="ruleForm.password" type="password"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      ruleForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //向后端请求登录业务
          this.$axios.post("/user/login",this.ruleForm).then(
              Response => {
                this.$store.commit("SET_TOKEN" , Response.data.data.token);
                this.$store.commit("SET_USERINFO" , Response.data.data.user);
                this.$message({
                  showClose: true,
                  message: Response.data.message,
                  type: 'success'
                });
                this.$router.replace("/");
              }
          )
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
    .login-ruleForm{
      padding: 200px;
      width: 450px;
      margin: 0 auto;
      text-align: center;
    }
</style>