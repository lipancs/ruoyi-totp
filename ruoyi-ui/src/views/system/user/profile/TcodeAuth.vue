<template>
    <div class="app-container">
    <el-button type="primary" size="mini" @click="getTcode">重置并激活</el-button>
    <el-button type="primary" size="mini" @click="resetUsertcode">关闭</el-button>
        <el-tag :type="success" >{{statusText}}</el-tag>
        <el-dialog  title="提示" :visible.sync="dialogVisible" width="20%" height="30%"  :before-close="handleClose">
            <img :src="codeUrl"  class="login-code-img"/>
            <el-form ref="form" :model="user" :rules="rules" label-width="80px">
            <el-form-item label="动态口令" prop="tcode">
            <el-input v-model="user.tcode" placeholder="请输入扫码后OTP软件的生成动态code用以验证并绑定，注意倒计时有效性"/>
            <el-button type="primary" @click="Tcodevaild">验证</el-button>
            </el-form-item>
            </el-form>
            <!-- <el-button type="primary" size="mini" @click="Tcodevaild">关闭</el-button> -->
        </el-dialog>
        <!-- <el-button type="primary" @click="getTcode">重置</el-button>
        <el-input v-model="tcode" :rules="rules" placeholder="test"></el-input>
        <el-button type="primary" @click="getTcode">关闭</el-button> 
        <el-tag size="large" type="success">标签二</el-tag>
        <el-dialog  title="提示" :visible.sync="dialogVisible" width="30%" height="80%"  :before-close="handleClose">
            <img :src="codeUrl"  class="login-code-img"/>
            <el-input v-model="tcode1" :rules="rules" placeholder="请输入扫码后OTP软件的生成动态code用以验证并绑定，注意倒计时有效性"></el-input>
            <el-button type="primary" @click="Tcodevaild">验证</el-button>
        </el-dialog> -->
    </div>
</template>
<script>

import request from '@/utils/request'
import { vaildTcode } from "@/api/system/user";
import {resetUserTcode} from "@/api/system/user";
import {tcodeStatus} from "@/api/system/user";
export default {
    data() {
      return {
        statusText:"GGGGGG",
        tcodeStatus:"warning",
        codeUrl:'',
        dialogVisible: false,
         user:{
             tcode:undefined, 
         },      
    rules: {
        tcode: [
        { required: true, message: "不能为空", trigger: "blur" }
        ]
            }
      };
    },
created() {
    this.gettcodeStatus();
  },  
methods: {
    getTcode() {
        this.dialogVisible=true;
        this.user.tcode=undefined;
  return request({
    
    url: '/system/user/profile/tcodesetup',
    method: 'get',   
  }
  ).then(res=>
  {
    console.log(res.img);
    this.codeUrl = "data:image/gif;base64," + res.img;
  })
},

    Tcodevaild()
    {this.$refs["form"].validate(valid => {

if (valid) {

    vaildTcode(this.user.tcode).then(response =>{this.$modal.msgSuccess("绑定成功!")});

}
    });
    },
     

    resetUsertcode()
    {
        resetUserTcode().then(response=>{this.$modal.msgSuccess("关闭成功!");})
    }
,

    gettcodeStatus()
    {
      tcodeStatus().then(response => 
      {    
        this.statusText=response.tcodeStatus;
      });

    }

,

    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.codeUrl="";
          })
          .catch(_ => {});
      }
    }
  };
</script>


<style rel="stylesheet/scss" lang="scss">
.el-dialog__body img {
    width: 100%;
    height: 100%;
  }
</style>