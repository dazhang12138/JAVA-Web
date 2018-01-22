var React = require('react');
var _ = require('lodash');
var {YYClass,YYCol,YYPage,YYMenu,YYMenuItem,YYDropdown,YYIcon,YYModal,YYUpload,YYButton,YYMessage,YYForm,YYFormItem,YYInput} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var YYChart = require('yylib-ui/chart/YYChart');
var ReactDOM = require('react-dom');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var URL = require('./Url');
require('./css/mainPage.css');
var FilesInages = require('./filesInages');
var THIS;
/*账户信息*/
var UserData;
/*账户显示文件夹地址*/
var UserFilesPath;
/*查询用户文件列表数据*/
var queryParam;
/*图表*/
var usageChart;
/*文件类型图标*/
var filesImagesSrc;

/*查询文件列表*/
function queryUserFileDateList(queryParam) {
    ajax.postJSON(URL.GETUSERFILES,queryParam,function (result) {
        var listTable = THIS.findUI('listTable');
        listTable.dataSource = result;
        THIS.refresh();
    })
}

/*设置面包屑*/
function setitemsdata() {
    var foldersPath = THIS.findUI('foldersPath');
    var split = UserFilesPath.split('/');
    var itemsdata = [];
    for (var i = 0;i < split.length; i++){
        var items = {
            title:split[i],
        }
        itemsdata.push(items);
    }
    foldersPath.itemsData = itemsdata;
}

/*新建文件夹检测唯一值名称*/
function newsFolderVerify(filesName) {
    var result = false;
    var listTable = THIS.findUI('listTable');
    var data = listTable.api.getDataSource();
    for (var i = 0; i < data.length; i++){
        if(data[i].type == '0' && data[i].fileName == filesName){
            result = true;
        }
    }
    return result;
}

/*保存新建文件夹*/
function saveNewFolder(queryParam) {
    ajax.postJSON(URL.NEWFOLDER,queryParam,function (result) {
        if(result){
            queryUserFileDateList(queryParam);
            YYMessage.success("创建文件夹成功");
        }else{
            YYMessage.error("创建文件夹失败");
        }
    })
}

/*表格双击事件*/
var onRowDoubleClick = function (record,index) {
    // console.log('行数据',record,index);
    if(record.type == 0){
        UserFilesPath = UserFilesPath + '/' + record.fileName;
        queryParam.filePath = UserFilesPath;
        queryUserFileDateList(queryParam);
        /*设置面包屑*/
        setitemsdata();
    }
};

/*表格行点击事件*/
var onRowClick = function (record,index) {
    var listTable = THIS.findUI('listTable');
    // listTable.api.clearSelectRowKeys();
}
/*删除*/
function deleteFiles(deletef) {
    ajax.postJSON(URL.DELETEFILES,deletef,function (result) {
        var flag = result.result;
        if(flag == 'error') {
            YYMessage.error('文件删除失败');
        }else{
            YYMessage.success('文件删除成功');
            UserData = result.data;
            alterChart();
            queryUserFileDateList(queryParam);
        }
    })
}

/*修改图表*/
function alterChart() {
    //使用银行家舍入法：四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一。
    var data = (UserData.fileSize/UserData.maxFileSize).toFixed(5);
    var seriesData = options.series[0].data[0];
    seriesData.value = data*100;
    // console.log(options,usageChart);
    usageChart.setOption(options);
}
/*页面初始化*/
/*logo*/
var LOGO = YYClass.create({
    render: function() {
        return (
            <YYCol span={4}>
                <a href={URL.LOGINPATH}>
                    <img style={{marginLeft:20,marginTop:20}} id="logo" src="./images/db_logo.png"/>
                </a>
            </YYCol>
        )}
});

/*菜单导航栏*/
var MENU = YYClass.create({
    getInitialState() {
        return {
            current: 'mail',
        };
    },
    handleClick(e) {
        console.log('click ', e);
        this.setState({
            current: e.key,
        });
    },
    render:function () {
       return(
           <YYCol span={17}>
               <YYPage >
                   <YYMenu onClick={this.handleClick}
                           selectedKeys={[this.state.current]}
                           mode="horizontal"
                           style={{marginTop:35}}
                   >
                       <YYMenuItem key="networkDisk">
                           <span style={{fontSize:20}}>网盘</span>
                       </YYMenuItem>
                       <YYMenuItem key="share">
                           <span style={{fontSize:20,marginLeft:10}}>分享</span>
                       </YYMenuItem>
                       <YYMenuItem key="more">
                           <span style={{fontSize:20,marginLeft:10}}>更多</span>
                       </YYMenuItem>
                   </YYMenu>
               </YYPage>
           </YYCol>
       )
    }
});

/*个人信息的菜单栏*/
function outLogin() {
    UserData = null;
    UserFilesPath = null;
    queryParam = null;
    THIS.routeTo('/DB');
}
function userMessage() {
    THIS.routeTo('/DB/userMessage',null,UserData);
}
const Usermenu = (
    <YYMenu>
        <YYMenuItem>
            <a onClick={userMessage}>个人资料</a>
        </YYMenuItem>
        <YYMenuItem>
            <a href="http://www.taobao.com/">开通VIP</a>
        </YYMenuItem>
        <YYMenuItem>
            <a href="http://www.taobao.com/">系统通知</a>
        </YYMenuItem>
        <YYMenuItem>
            <a onClick={outLogin}>退出登录</a>
        </YYMenuItem>
    </YYMenu>
);

/*顶部个人信息*/
var INFORMATION = YYClass.create({
   render:function () {
       return(
           <YYCol span={3}>
               <YYPage style={{marginTop:44,float:"left",marginLeft:40}}>
                   <YYDropdown overlay={Usermenu}>
                                <span className="ant-dropdown-link" style={{fontSize:20}} href="#">
                                  欢迎您 :  {UserData.name} <YYIcon type="down" />
                                </span>
                   </YYDropdown>
               </YYPage>
           </YYCol>
       )
   }
});

/*上传弹窗*/
var UPLOADPOPUPWINDOW = YYClass.create({
    beforeUpload: function (file) {
        //文件上传之前的钩子
        UserData.fileSize = UserData.fileSize + file.size;
        alterChart();
    },
    onChange: function (info) {
        if (info.file.status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
            YYMessage.success(`${info.file.name} 上传成功。`);
            queryUserFileDateList(queryParam);
        } else if (info.file.status === 'error') {
            YYMessage.error(`${info.file.name} 上传失败。`);
        }
    },
    handleCancel(e) {
        var uploadPopupWindow = THIS.findUI('uploadPopupWindow');
        uploadPopupWindow.visible = false;
        THIS.refresh();
    },
    handleOk() {
        var uploadPopupWindow = THIS.findUI('uploadPopupWindow');
        uploadPopupWindow.visible = false;
        THIS.refresh();
    },
   render:function () {
       return <YYModal title="上传文件" visible={this.props.visible}
                    onOk={this.handleOk} onCancel={this.handleCancel}>
               <YYUpload
                   name="file"
                   action={URL.UPLOADFILE}
                   data={{userName:UserData.name,path:UserFilesPath}}
                   multiple = {true}
                   // headers={{authorization: 'authorization-text'}}
                   beforeUpload={this.beforeUpload}
                   onChange={this.onChange}>
                   <YYButton type="ghost">
                       <YYIcon type="upload" />点击上传
                   </YYButton>
               </YYUpload>
           </YYModal>
   }
});

/*新建文件夹弹窗*/
const formItemLayout = {
    labelCol: { span: 7 },
    wrapperCol: { span: 12 },
};
var NEWFOLDER = YYClass.create({
    handleCancel(e) {
        var NewFolder = THIS.findUI('NewFolder');
        NewFolder.visible = false;
        THIS.refresh();
    },
    handleOk() {
        var NewFolder = THIS.findUI('NewFolder');
        NewFolder.visible = false;
        THIS.refresh();
    },
    handleSubmit: function (e) {
        e.preventDefault();
        this.props.form.validateFields(function (errors, values) {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            var queryParam = {
                userId:UserData._id,
                filePath:UserFilesPath,
                folderName:values.folderName,
            };
            saveNewFolder(queryParam);
        });
        this.props.form.resetFields();
    },
    userExists: function (rule, value, callback) {
        if (!value) {
            callback();
        } else {
            setTimeout(() => {
                /*命名规范*/
                if(value.indexOf("/")>-1 || value.indexOf("\\")>-1 || value.indexOf(":")>-1
                    || value.indexOf("\"")>-1 ||value.indexOf("*")>-1 || value.indexOf("?")>-1 || value.indexOf("<")>-1
                    || value.indexOf(">")>-1 || value.indexOf("|")>-1|| value.indexOf(".")>-1){
                    callback([new Error('文件名不能包含下列任何字符 ：/\\:*?\"<>|.')]);
                }else if (newsFolderVerify(value)) {
                    /*唯一值校验*/
                    callback([new Error('抱歉，该文件夹已存在。')]);
                } else {
                    callback();
                }
            }, 800);
        }
    },
    handleReset: function (e) {
        e.preventDefault();
        this.props.form.resetFields();
    },
    render:function () {
        var { getFieldProps, isFieldValidating, getFieldError } = this.props.form;
        const nameProps = getFieldProps('folderName', {
            rules: [
                { required: true, whitespace: true, message: '请填写文件夹名称'},
                { validator: this.userExists },
            ],
        });
        return <YYModal title="新建文件夹" visible={this.props.visible}
                        onOk={this.handleOk} onCancel={this.handleCancel}>
            <YYForm horizontal>
                <YYFormItem
                    {...formItemLayout}
                    label="文件夹名称"
                    hasFeedback
                    help={isFieldValidating('folderName') ? '校验中...' : (getFieldError('folderName') || []).join(', ')}>
                    <YYInput {...nameProps}/>
                </YYFormItem>
                <YYFormItem wrapperCol={{ span: 12, offset: 7 }}>
                    <YYButton type="primary" onClick={this.handleSubmit}>创建</YYButton>
                    &nbsp;&nbsp;&nbsp;
                    <YYButton type="ghost" onClick={this.handleReset}>重置</YYButton>
                </YYFormItem>
            </YYForm>
        </YYModal>
    }

});
NEWFOLDER = YYForm.create()(NEWFOLDER);

/*修改文件页面*/
var ALTERFILES = YYClass.create({
    handleCancel(e) {
        var alterFiles = THIS.findUI('alterFiles');
        alterFiles.visible = false;
        THIS.refresh();
    },
    handleOk() {
        var alterFiles = THIS.findUI('alterFiles');
        alterFiles.visible = false;
        THIS.refresh();
    },
    render:function () {
        return <YYModal title="修改文件" visible={this.props.visible}
            onOk={this.handleOk}  onCancel={this.handleCancel} >
            </YYModal>
    }
});

/*空间使用情况图表数据*/
const options = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%",
        position:['18%',10]
    },
    series:[
        {
            name: '已存储',
            type: 'gauge',
            detail: {
                formatter: '{value}%',
                fontSize:15
            },
            data: [{
                value: 50,
                name: '空间使用情况'
            }],
            axisLine:{
                lineStyle:{
                    width:10
                }
            },
            pointer:{
                width:4
            },
            axisLabel:{
                distance:-35
            },
            splitLine:{
                length:15
            },
            title:{
                offsetCenter:[0,'100%'],
                fontSize:15
            }
        }
    ]
};
/*图表数据*/
var series1 = {
    name: '已存储',
    type: 'gauge',
    detail: {
        formatter: '{value}%',
        fontSize:15
    },
    data: [{
            value: 50,
            name: '空间使用情况'
    }],
    axisLine:{
        lineStyle:{
            width:10
        }
    },
    pointer:{
        width:4
    },
    axisLabel:{
        distance:-35
    },
    splitLine:{
        length:15
    },
    title:{
        offsetCenter:[0,'100%'],
        fontSize:15
    }
};
/*图表实例*/
var Mychart = YYClass.create({
    ready:function (chart) {
        chart.on('click', function (params) {
            // console.log('监测待办排行',params);
            var data = {
                true:true,
            }
            THIS.routeTo('/DB/userMessage',data,UserData);
        });
        usageChart = chart;
    },
   render:function () {
       return (
           <div>
               <YYChart {...options} onReady={this.ready} width={230} height={200}>
                   <YYChart.Bar {...series1}/>
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
        return <div id="chartdemo1" style={{position:'fixed',bottom:0,left:0,right:0,width:100}}></div>
    }
})

//页面初始化
var EventHanlder = {
    "alter":{
        "onClick":function () {
            var listTable = THIS.findUI('listTable');
            var selectedRowData = listTable.api.getSelectedRowData();
            if(selectedRowData.length > 1){
                YYMessage.info("修改操作只能勾选一个文件!");
            }else if(selectedRowData.length > 0){
                var alterFiles = THIS.findUI('alterFiles');
                alterFiles.visible = true;
                THIS.refresh();
                // YYMessage.success("修改");
            }else{
                YYMessage.info("请勾选需要修改的文件");
            }
        }
    },
    "breakOne":{
        "onClick":function () {
            var end = UserFilesPath.lastIndexOf('/');
            if(end > -1){
                UserFilesPath = UserFilesPath.substring(0,end);
                queryParam.filePath = UserFilesPath;
                queryUserFileDateList(queryParam);
                /*设置面包屑*/
                setitemsdata();
            }
        }
    },
    "breakAll":{
        "onClick":function () {
            UserFilesPath = UserData.name;
            queryParam.filePath = UserFilesPath;
            queryUserFileDateList(queryParam);
            /*设置面包屑*/
            setitemsdata();
        }
    },
    "download":{
        "onClick":function () {
            console.log('下载');
        }
    },
    "delete":{
        "onClick":function () {
            var listTable = THIS.findUI('listTable');
            var selectedRowData = listTable.api.getSelectedRowData()
            if(selectedRowData.length > 0){
                var deletefolder = [];
                var deletefile = [];
                for (var i = 0;i<selectedRowData.length;i++){
                    var row = selectedRowData[i];
                    if(row.type == 0){
                        var d = {
                            _id:row._id,
                            folderName:row.fileName,
                        }
                        deletefolder.push(d);
                    }else{
                        var d = {
                            _id:row._id,
                            fileName:row.fileName,
                        }
                        deletefile.push(d);
                    }
                }
                var deletef = {
                    deletefolder:deletefolder,
                    deletefile:deletefile,
                    folderPath:UserFilesPath,
                    userId:UserData._id,
                };
                deleteFiles(deletef);
            }else{
                YYMessage.error('请勾选需要删除的文件');
            }
        }
    },
    "addFolder":{
        "onClick":function () {
            var NewFolder = THIS.findUI('NewFolder');
            NewFolder.visible = true;
            THIS.refresh();
        }
    },
    "uploadFile":{
        "onClick":function () {
            var uploadPopupWindow = THIS.findUI('uploadPopupWindow');
            uploadPopupWindow.visible = true;
            THIS.refresh();
        }
    },
    "P005023":{
        onViewWillMount:function(options){
            // console.log('page onViewWillMount',options);
            filesImagesSrc = new FilesInages();
            THIS = this;
            /*获取登录数据*/
            UserData = this.getRouteParams();
            UserFilesPath = UserData.name;
            /*默认选中的树节点*/
            var leftMenu = THIS.findUI('leftMenu');
            leftMenu.selectedKeys = ['1515737467367_5842'];
            queryParam = {
                userId: UserData._id,
                filePath:UserFilesPath,
            }
            queryUserFileDateList(queryParam);
            /*设置表格事件*/
            var listTable = THIS.findUI('listTable');
            listTable.onRowDoubleClick = onRowDoubleClick;
            listTable.rowClickCheckable = true;
            listTable.onRowClick = onRowClick;
            listTable.children[2].render = function (text, record, index) { //预警等级 图片
                var imageSrc=filesImagesSrc.getFileTypeImage(record.type,record.fileType);
                var color = 'blue';
                if(record.type == 0){
                    color = 'red';
                }
                return <YYIcon type={imageSrc} style={{color:color}}/>
            }
            /*设置面包屑*/
            setitemsdata();
            THIS.refresh();
        }
        ,onViewDidMount:function(options){
            // console.log('page onViewDidMount',options);
            alterChart();
        }
        ,onViewWillUpdate:function(options){
            // console.log('page onViewWillUpdate',options);
        }
        ,onViewDidUpdate:function(options){
            // console.log('page onViewDidUpdate',options);
        }
    }
}

var MyParser = {};
MyParser.logo = LOGO;
MyParser.menu = MENU;
MyParser.information = INFORMATION;
MyParser.uploadPopupWindow = UPLOADPOPUPWINDOW;
MyParser.NewFolder = NEWFOLDER;
MyParser.alterFiles = ALTERFILES;
MyParser.ChartDemo1 = ChartDemo1;

var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005023" uiEvent={EventHanlder}
                             uiParser={MyParser}/>
    }
});
module.exports = SimplePage;