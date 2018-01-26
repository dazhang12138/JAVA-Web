var React = require('react');
var _ = require('lodash');
var {YYClass,YYMessage,YYFormItem,YYInput} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var YYChart = require('yylib-ui/chart/YYChart');
var ReactDOM = require('react-dom');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var URL = require('./Url');
var MessageKeys = require('./MessageKeys');
require('./userMessageCSS.css');
var THIS;
var UserDate;
//图表变量
var Chart;

/*各类文件类型使用情况图表*/
const options = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['未使用','文档','视频','音频','图片','种子','其他']
    },
    series: [
        {
            name:'各类文件使用控件统计',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[]
        }
    ]
};

var series = {
    name:'各类文件使用控件统计',
    type:'pie',
    radius: ['50%', '70%'],
    avoidLabelOverlap: false,
    label: {
        normal: {
            show: false,
            position: 'center'
        },
        emphasis: {
            show: true,
            textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
            }
        }
    },
    labelLine: {
        normal: {
            show: false
        }
    },
    data:[
        {value:335, name:'未使用'},
        {value:310, name:'视频'},
        {value:234, name:'音频'},
        {value:135, name:'图片'},
        {value:1548, name:'种子'},
        {value:10, name:'其他'}
    ]
};

/*图表实例*/
var Mychart = YYClass.create({
    ready:function (chart) {
        chart.on('click', function (params) {
            console.log('监测待办排行',params);
        });
        Chart = chart;
        queryFileTypeNumber();
    },
    render:function () {
        return (
            <div>
                <YYChart {...options} onReady={this.ready}>
                    <YYChart.Bar {...series} />
                </YYChart>
            </div>
        )
    }
});

/*图表*/
var ChartDemo1 = YYClass.create({
    componentDidMount: function () {
        ReactDOM.render(<Mychart />, document.getElementById('chartdemo1'));
    },
    render: function () {
        return <div id="chartdemo1"></div>
    }
})

/*查询用户存储空间使用情况的详情*/
function queryFileTypeNumber() {
    var data = {
        userId : UserDate._id,
    };
    ajax.postJSON(URL.SPACESTORAGE,data,function (result) {
        var redata = options.series[0].data = [];
        for (var i = 0; i < result.length; i++){
            var d = {
                name:result[i].name,
                value:result[i].size,
            };
            redata.push(d);
        }
        Chart.setOption(options);
    });
}


/*根据出生日期算出年龄*/
function jsGetAge(strBirthday){
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];

    var d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();

    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0){returnAge = ageDiff - 1;}
                else{returnAge = ageDiff ;}
            }else{
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0){returnAge = ageDiff - 1;}
                else{returnAge = ageDiff ;}
            }
        }else{returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    return returnAge;//返回周岁年龄
}
function saveAge(date) {
    var age = THIS.findUI('age');
    if(date == null){
        age.value = '';
    }else{
        age.value = jsGetAge(date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate());
    }
    THIS.refresh();
}
/*修改编辑基本信息的状态*/
function editType() {
    var sex = THIS.findUI('sex');
    sex.disabled = !sex.disabled;
    var openEdit = THIS.findUI('openEdit');
    if(openEdit.visible == null){
        openEdit.visible = false;
    }else{
        openEdit.visible = !openEdit.visible;
    }
    var saveMessage = THIS.findUI('saveMessage');
    saveMessage.visible = !saveMessage.visible;
    var closeEdit = THIS.findUI('closeEdit');
    closeEdit.visible = !closeEdit.visible;
    var birthday = THIS.findUI('birthday');
    birthday.disabled = !birthday.disabled;
    var idNumber = THIS.findUI('idNumber');
    idNumber.disabled = !idNumber.disabled;
    var phoneNumber = THIS.findUI('phoneNumber');
    phoneNumber.disabled = !phoneNumber.disabled;
    THIS.refresh();
}
/*修改编辑登录信息的状态*/
function editLoginType() {
    var openEditLogin = THIS.findUI('openEditLogin');
    if(openEditLogin.visible == null){
        openEditLogin.visible = false;
    }else{
        openEditLogin.visible = !openEditLogin.visible;
    }
    var alterMessage = THIS.findUI('alterMessage');
    alterMessage.visible = !alterMessage.visible;
    var closeEditLogin = THIS.findUI('closeEditLogin');
    closeEditLogin.visible = !closeEditLogin.visible;
    var userpwd = document.getElementById('userpwd');
    userpwd.disabled = !userpwd.disabled;
    var pwd1 = document.getElementById('pwd1');
    pwd1.disabled = !pwd1.disabled;
    var pwd2 = document.getElementById('pwd2');
    pwd2.disabled = !pwd2.disabled;
    THIS.refresh();
}
var EventHanlder = {
    "openEditLogin":{
        //启用登录信息编辑
        onClick:function () {
            document.getElementById('userpwd').type = 'password';
            document.getElementById('pwd1').type = 'password';
            document.getElementById('pwd2').type = 'password';
            editLoginType();
        }
    },
    "closeEditLogin":{
        //取消登录信息编辑
        onClick:function () {
            editLoginType();
        }
    },
    "closeEdit":{
        //取消基本信息编辑
        onClick:function () {
            editType();
        }
    },
    "openEdit":{
        //启动基本信息编辑
        onClick:function () {
            editType();
        }  
    },
    "goBack":{
        onClick:function () {
            THIS.goBack();
        }
    },
    "saveMessage":{
        onClick:function () {
            var basicInformation = THIS.findUI('basicInformation');
            basicInformation.api.validateFields(function (errors,values) {
                if(!errors){
                    values.userId = UserDate._id;
                    ajax.postJSON(URL.UPDATEUSERMESSAGE,values,function (result) {
                        if(result.result != 'error'){
                            YYMessage.success("修改信息成功");
                            UserDate = result;
                            editType();
                        }else{
                            YYMessage.error("修改信息失败");
                        }
                    });
                }
            });
        }
    },
    "alterMessage":{
        onClick:function () {
            var loginFrom = THIS.findUI('loginFrom');
            loginFrom.api.validateFields(function (errors,values) {
                if(!!errors){
                    YYMessage.error('修改失败！请填写密码！');
                    return;
                }
                if(values.pwd1 != values.pwd2){
                    YYMessage.error('修改失败！两次输入密码不匹配！');
                    return;
                }
                var data = {
                    userId:UserDate._id,
                    userpwd:values.userpwd,
                    pwd1:values.pwd1
                };
                ajax.postJSON(URL.ALTERUSERPWD,data,function (result) {
                    if(result == 'OK'){
                        YYMessage.info("修改密码成功");
                        THIS.routeTo('/DB');
                    }else if(result == 'loginError'){
                        YYMessage.error("修改密码失败!登录密码错误!");
                    }else{
                        YYMessage.error("修改密码失败!");
                    }
                })
            });
        }
    },
    "message":{
        onChange:function (keys) {
           if(keys == MessageKeys.BASIC){

           }else if(keys == MessageKeys.LOGIN){

           }else{

           }
        }  
    },
    "P005076":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
            THIS = this;
            UserDate = THIS.getRouteParams();
            var name = THIS.findUI('name');
            name.value = UserDate.name;
            var startTime = THIS.findUI('startTime');
            startTime.defaultValue = UserDate.userStartTime;
            var email = THIS.findUI('email');
            email.value = UserDate.userEmail;
            var userName = THIS.findUI('userName');
            userName.value = UserDate.userName;
            var birthday = THIS.findUI('birthday');
            birthday.onChange = saveAge;
            if(UserDate.age != null){
                var age = THIS.findUI('age');
                age.value = UserDate.age;
            }
            if(UserDate.sex != null){
                var sex = THIS.findUI('sex');
                sex.value = UserDate.sex;
            }
            if(UserDate.birthday != null){
                console.log(UserDate.birthday);
                var birthday = THIS.findUI('birthday');
                birthday.defaultValue = UserDate.birthday;
            }
            if(UserDate.idNumber != null){
                var idNumber = THIS.findUI('idNumber');
                idNumber.value = UserDate.idNumber;
            }
            if(UserDate.phoneNumber != null){
                var phoneNumber = THIS.findUI('phoneNumber');
                phoneNumber.value = UserDate.phoneNumber;
            }
        }
        ,onViewDidMount:function(options){
            console.log('page onViewDidMount',options);
        }
        ,onViewWillUpdate:function(options){
            console.log('page onViewWillUpdate',options);
        }
        ,onViewDidUpdate:function(options){
            console.log('page onViewDidUpdate',options);
        }
    }
}

var MyParser = {};
MyParser.saveChart = ChartDemo1;

var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005076" uiEvent={EventHanlder}
                             uiParser={MyParser}/>
    }
});
module.exports = SimplePage;