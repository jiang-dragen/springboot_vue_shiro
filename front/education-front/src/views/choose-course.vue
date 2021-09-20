<template>
    <div>
      <el-table
          :data="tableData"
          border
          style="width: 100%">
        <el-table-column
            label="序号" width="50px">
          <template slot-scope="solt">
            {{ (pageNum - 1) * pageSize + solt.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column
            prop="course.courseCode"
            label="课程代码" width="80px">
        </el-table-column>
        <el-table-column
            prop="course.courseName"
            label="课程名称" width="80px">
        </el-table-column>
        <el-table-column
            prop="course.courseType"
            label="性质" width="70px">
        </el-table-column>
        <el-table-column
            prop="course.courseCredit"
            label="学分" width="60px">
        </el-table-column>
        <el-table-column
            prop="course.courseClass"
            label="班号" width="60px">
        </el-table-column>
        <el-table-column
            prop="school.schoolName"
            label="学院" width="110px">
        </el-table-column>
        <el-table-column
            prop="teacher.nickname"
            label="老师" width="70px">
        </el-table-column>
        <el-table-column
            prop="course.courseTime"
            label="时间">
        </el-table-column>
        <el-table-column
            prop="course.coursePlace"
            label="地点">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button @click="chooseCourse(scope.row.course.courseId)" type="primary">选课</el-button>
          </template>

        </el-table-column>
      </el-table>
        <el-pagination class="page"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page= "pageNum"
            :page-sizes="[5, 1, 20, 30]"
            :page-size= "pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total= "total">
        </el-pagination>

    </div>
</template>

<script>
export default {
  name: "choose-course",
  data() {
    return{
      tableData: [],
      pageNum: '',
      pageSize: '',
      total: ''
    }
  },
  methods:{
    getList(){
      let pageNum = this.pageNum;
      let pageSize = this.pageSize;
      let userId = this.$store.getters.getUser.userId;
      this.$axios.get(`/course/showThis?userId=${userId}&pageNum=${pageNum}&pageSize=${pageSize}`,
          {
            headers: {
              "Authorization": this.$store.getters.getToken
            }
          }).then(Response => {
        var data = Response.data.data
        if (data == null){
          this.tableData = []
          this.total = 0
        }
        this.tableData = Response.data.data.list
        this.total = Response.data.data.total
      })
    },
    handleSizeChange(val){
      this.pageSize = val;
      this.getList()
    },
    handleCurrentChange(val){
      this.pageNum = val;
      this.getList()
    },
    chooseCourse(val){
      this.$axios.post(`/course/choose`,
          {
                userId: this.$store.getters.getUser.userId,
                courseId: val
          },
          {
            headers: {
              "Authorization": this.$store.getters.getToken
            }
          }).then(Response => {
        this.$message({
          type: 'success',
          message: Response.data.message
        });
        this.getList()
      })
    }
  },
  created() {
    this.getList()
  }
}
</script>

<style scoped>
.page {
  margin-top: 15px;
  text-align: center;
}
</style>