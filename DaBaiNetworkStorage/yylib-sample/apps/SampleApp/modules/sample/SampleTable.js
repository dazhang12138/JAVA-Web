/**
 * Created by wuhao on 2016/11/7.
 */
var React = require('react');
var {YYClass,YYPage,YYRow,YYToolbar,YYButton,YYTable,YYMessage,YYCol,YYBreadcrumb,YYPopconfirm} = require('yylib-ui');
var _ = require('lodash');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var {Link} = require('react-router');
var {URL_HOME,MODULE_URL} = require('../../actions/RestUrl');
var formatUtil = require('yylib-utils/formatUtils')

var mapStateToProps = function (state) {
    return {
        tableData: state.sample.tableData,
    }
}

var SamplTable = YYClass.create({
    handleClickDel: function (record) {
        var self = this
        ajax.delJSON(MODULE_URL.SAMPLE+'/'+record.id, function (result) {
            if (result&&result.success&&result.backData) {
                YYMessage.success('删除成功');
                self.props.sendAction('loadTable',result.backData)
            } else {
                YYMessage.error(`删除失败${result.backMsg}`);
            }
        })
    },
    handleClickEdit: function (record) {
        //console.log('record' ,record);
    },
    componentDidMount: function () {
        var self = this
        ajax.getJSON(MODULE_URL.SAMPLE, function (result) {
            //console.log('result.backData', result.backData)
            self.props.sendAction('loadTable', result.backData);
        });
    },
    render: function () {
        var self = this
        const columns = [{
            title: '主键',
            dataIndex: 'id',
            key: 'id',
            isShow:false
        },{
            title: '学号',
            dataIndex: 'code',
            key: 'code',
        }, {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
        },{
            title: '性别',
            dataIndex: 'gender',
            key: 'gender',
        },{
            title: '名次',
            dataIndex: 'ranking',
            key: 'ranking',
        },{
            title: '专业',
            dataIndex: 'major',
            key: 'major',
        },{
            title: '管理员',
            dataIndex: 'admin',
            key: 'admin',
            render:function (text,record,index) {
                if(record.admin){
                    return (<div>是</div>)
                }else{
                    return (<div>否</div>)
                }
            }
        },{
            title: '修改日期',
            dataIndex: 'modifyDate',
            key: 'modifyDate',
            render:function (text,record,index) {
                return formatUtil.formatDate(new Date(record.modifyDate), 'yyyy-MM-dd')
            }
        },{
            title: '操作',
            dataIndex: 'action',
            key: 'action',
            width: '30%',
            render:function (text,record,index) {
                record.modifyDate = formatUtil.formatDate(new Date(record.modifyDate), 'yyyy-MM-dd')
                //console.log('record', record)
                return (
                    <div>
                        <YYCol span="5">
                            <YYPopconfirm placement="leftBottom" title="确定删除此应用?" onConfirm={self.handleClickDel.bind(null,record)} okText="是" cancelText="否">
                                <YYButton type="primary" icon="plus" size="small" >删除</YYButton>
                            </YYPopconfirm>
                        </YYCol>
                        <YYCol span="4">
                            <Link to="/sample/form/" query={ record }><YYButton type="primary" icon="plus" size="small" onClick={self.handleClickEdit.bind(null,record)}>编辑</YYButton></Link>
                        </YYCol>
                    </div>
                )
            }
        }];
        var { tableData } = this.props
        var breadcrumbItems = [
            {title:'主页',href:URL_HOME},
            {title:'示例一'}
        ];
        return (
            <YYPage>
                <YYBreadcrumb itemsData={breadcrumbItems}/>
                <YYToolbar>
                    <Link  to="/sample/form/"><YYButton type="primary" icon="plus">新建</YYButton></Link>
                </YYToolbar>
                <YYTable columns={columns} dataSource={tableData}/>
            </YYPage>
        )
    }
})

module.exports = ReduxUtils.connect(SamplTable, mapStateToProps);