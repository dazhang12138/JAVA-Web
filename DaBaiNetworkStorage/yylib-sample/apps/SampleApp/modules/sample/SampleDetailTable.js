/**
 * Created by wuhao on 2016/11/21.
 */
var React = require('react');
var {YYClass,YYPage,YYToolbar,YYCol,YYButton,YYAccordion,YYRadioGroup,YYRadio,YYSelect,YYOption,YYSwitch,YYDatePicker,
    YYAccordions,YYForm,YYInput, YYFormItem,YYMessage,YYBreadcrumb,YYInputNumber,YYRate, YYEditTable,YYIcon} = require('yylib-ui');
var _ = require('lodash');
var {Link} = require('react-router')
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var {URL_HOME,MODULE_URL} = require('../../actions/RestUrl');

var mapStateToProps = function (state) {
    return {
        detailTableData: state.sample.detailTableData,
    }
}

/**子表相关**/
//编辑表格的相关回调事件
var EditTableEvents = {
    // 切换编辑状态
    onEditRow:function(rowData, index) {
        /****此处可做一些校验****/
        this.editRow(index);//将指定行标记为编辑态，此方法由YYEditTable提供
    }
    // 提交保存编辑
    ,onSaveRow:function(rowData, index) {
        rowData.personId = this.props.id
        /****此处可做一些校验****/
        this.saveRow(index,rowData);//将指定行标记为非编辑态，并保存修改后的数据，此方法由YYEditTable提供
        this.props.sendAction('loadDetailTable', this.getRowData())
    }
    // 删除当前行
    ,onDelRow:function(rowData, index){
        /****此处可做一些校验****/
        var self = this
        this.delRow(rowData.id,function(afterRowData){
            console.log('当前行删除后剩下的数据',afterRowData);
            self.props.sendAction('loadDetailTable', afterRowData)
        });//将指定行进行删除，此方法由YYEditTable提供
        console.log('this.props.detailTableData',this.props.detailTableData)
    }
    // 更改单元格数据 rowData-行数据、index-行索引、dataIndex-列索引名、event-事件对象或者值
    ,onCellChange:function(rowData, index, dataIndex, event) {
        //获取修改后的单元格数据，注意：此处依据输入类型不同，取值行为需要进行对应调整（如：参照、勾选、输入框等），通过dataIndex可判断当前是哪列，即可明确取值行为。
        var newVal = (event.target)?event.target.value:event;
        this.saveCell(index,dataIndex,newVal);//修改指定单元格的值，此方法由YYEditTable提供
    }
}

var columns = [{
    title: '分数',
    dataIndex: 'score',
    key: 'score',
    render: function (value, rowData, index) {
        if (rowData.isEdit) {//编辑态显示
            return <YYInputNumber defaultValue={value} onChange={EditTableEvents.onCellChange.bind(this, rowData, index, 'score')}/>
        } else {//非编辑态显示
            return <div>{value}</div>
        }
    }
},{
    title:'科目',
    dataIndex: 'course',
    key: 'course',
    render:function (value, rowData, index) {
        if (rowData.isEdit) {//编辑态显示
            return <YYInput defaultValue={value} onChange={EditTableEvents.onCellChange.bind(this, rowData, index, 'course')}/>
        } else {//非编辑态显示
            return <div>{value}</div>
        }
    }
},{
    title: '操作',
    dataIndex: 'oper',
    render:function(text,rowData,index){
        var edit;
        if (rowData.isEdit) {//编辑态显示
            edit = (<YYIcon type="check" title="保存" onClick={EditTableEvents.onSaveRow.bind(this, rowData, index)}/>);
        } else {//非编辑态显示
            edit = (<YYIcon type="edit" title="编辑" onClick={EditTableEvents.onEditRow.bind(this, rowData, index)}/>);
        }
        return [edit ,<YYIcon type="delete" title="删除" onClick={EditTableEvents.onDelRow.bind(this, rowData, index)}/>]
    }
}]

var SampleDetailTable = YYClass.create({
    //添加一行
    addRowClick:function(){
        //直接添加一行到首行
        this.refs.myEditTable.addRow();
    },
    //删除勾选行
    delRowClick:function(){
        //获取勾选的行keys
        var selectedKeys = this.refs.myEditTable.getSelectedRowKeys();
        //删除勾选的行数据
        this.refs.myEditTable.delRow(selectedKeys);
    },
    //打印全部编辑行数据
    printAllRows:function(){
        var allRows = this.refs.myEditTable.getRowData();
        console.log('编辑表格的数据:',allRows);
    },
    //加载子表
    loadDetailTable:function (sendAction,id) {
        ajax.getJSON(MODULE_URL.SAMPLE+'/'+id+'/detail', function (result) {
            console.log('result.backData', result.backData)
            sendAction('loadDetailTable', result.backData);
        })
    },
    componentDidMount:function () {
        this.loadDetailTable(this.props.sendAction,this.props.id)
    },
    render: function() {
        return (
            <YYPage>
                <YYToolbar>
                    <YYButton type="primary" icon="plus" onClick={this.addRowClick}>添加</YYButton>
                    <YYButton type="error" icon="delete" ghost onClick={this.delRowClick}>删除</YYButton>
                    <YYButton onClick={this.printAllRows}>获取编辑行数据(打开Console查看)</YYButton>
                </YYToolbar>
                <YYEditTable {...this.props}
                             ref="myEditTable" rowKey="id"
                             dataSource={this.props.detailTableData}
                             columns={columns}
                             personId={this.props.id}
                             sendAction={this.props.sendAction} />
            </YYPage>
        )
    }
});

module.exports = ReduxUtils.connect(SampleDetailTable, mapStateToProps)