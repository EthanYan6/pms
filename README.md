
# 北航流程优化算法接口文档梳理

## 方法

POST

## URL

127.0.0.1:8090/pms/projOpt/webOptResult

## 请求参数

数据交互格式：JSON

### 1.请求参数

> 仅输入与待优化项目任务相关的信息，包含下列必要字段，除特殊说明外，不能缺少或为空
>
> 优先级相关处理：如果不需要优先级，那么都设成一样的就可以了
>
> 所有时间数据使用时间戳，北航的直接将时间转换为了时间戳
>
> 沟通后所有的占用时长都直接填写天数即可



项目：project

| 参数名                 | 类型   | 备注               |
| ---------------------- | ------ | ------------------ |
| projUid                | String | 项目全局唯一编号   |
| projName               | String | 项目名称（非必须） |
| projPlanDur            | Float  | 项目预计工期       |
| projEarlyStartDateTime | Long   | 项目最早开始时间   |
| projLateFinishDateTime | Long   | 项目最晚完成时间   |

 

任务：task

| 参数名                 | 类型    | 备注                                           |
| ---------------------- | ------- | ---------------------------------------------- |
| taskUid                | String  | 任务全局唯一编号                               |
| taskName               | String  | 任务名称（非必须）                             |
| taskType               | Integer | 任务类型（非必须）                             |
| taskProjUid            | String  | 任务所属项目UID                                |
| taskPriority           | Integer | 任务优先级                                     |
| taskPlanDur            | Float   | 任务预计工期（直接填天数即可）                 |
| taskEarlyStartDateTime | Long    | 任务最早开始时间（可以为空）                   |
| taskLateFinishDateTime | Long    | 任务最晚完成时间                               |
| taskWorkModel          | Integer | 任务执行模式（现在没有差别了，都填1 就可以了） |

>   taskType：任务类型，0主线任务；1辅线任务；2质量控制；3安全控制；4分合节点任务；5黑盒任务。如果没有,可以直接设置为 0
>
>   taskPriority：任务优先级，1普通，2重要，4重大。
>
>   taskPlanDur：任务预计工期，一位小数，整数部分表示天数，小数点后一位表示小时数。
>
>   taskWorkModel：任务执行模式，0普通模式，按照朝八晚六工作时间；1连续模式，按照全天24小时工作时间。工期为天数后，普通与连续都可以，实际上这两个都是代表一天，只是一天中工作时间不同，原来我们是精确到小时的，所以会有这种差别，改成天后就没有区别了。选择普通就是从早8点开始，选择连续就行从0点开始。

 

任务与任务组关系：taskGroup

| 参数名            | 类型   | 备注                         |
| ----------------- | ------ | ---------------------------- |
| taskGroupUid      | String | 任务与任务组关系全局唯一编号 |
| taskGroupTaskUid  | String | 任务UID                      |
| taskGroupGroupUid | String | 包含该任务的任务组UID        |

> 一个流程中可以包含多个任务组，每个任务组是一串连续的任务，同一个任务组中的任务是可以调换顺序的串行任务。
>
> 如果任务都不可以调换，可以设置为空数组。

 

任务连接：taskLink

| 参数名             | 类型   | 备注        |
| ------------------ | ------ | ----------- |
| taskLinkPreTaskUid | String | 紧前任务UID |
| taskLinkSucTaskUid | String | 紧后任务UID |

 

任务资源方案：taskResPlan

| 参数名          | 类型    | 备注                     |
| --------------- | ------- | ------------------------ |
| resPlanUid      | String  | 任务资源方案全局唯一编号 |
| resPlanTaskUid  | String  | 资源方案所属任务UID      |
| resPlanPriority | Integer | 资源方案的优先级         |

>   资源方案优先级从1开始，优先级依次递减
>
>   resPlanUid  这个资源方案是在某个任务上创建的，所以叫任务资源方案，所以不需要拼接，只要是全局唯一编号就可以
>
>   resPlanTaskUid的含义就是说明该资源方案属于何种任务
>
>   resPlanPriority 这个是人为定义的，用于划分资源方案选择的优先级。如果没有优先级，可以都设置为 1.
>
>   一个任务对应一个或多个资源方案，一个资源方案对应一个或多个资源需求，一个资源需求是对一个资源配置各种信息，比如计划占用时间等等

 

任务资源需求：taskResReq

| 参数名             | 类型    | 备注                                                  |
| ------------------ | ------- | ----------------------------------------------------- |
| resReqUid          | String  | 任务资源需求全局唯一编号                              |
| resReqResPlanUid   | String  | 资源需求所属资源方案UID                               |
| resReqResType      | Integer | 所需资源的类型                                        |
| resReqResUid       | String  | 所需资源的UID                                         |
| resReqResWork      | Float   | 计划占用时长（与任务工期一致）                        |
| resReqResAmount    | Float   | 计划占用数量（如果是独占资源，比如设备，直接设置为0） |
| resReqResWorkModel | Integer | 资源工作模式（现在没有差别了，都填1 就可以了）        |

>   resReqResType：所需资源类型，0人力，1设备，2场地，3知识
>
>   resReqResWorkModel：资源工作模式，0普通模式，1连续模式。
>
>   resReqResWork：计划占用时长是设备的使用时间，可以比任务时间短，也可以直接设成一样的。任务时间通过资源方案找到。五院里面任务与资源占用一般都是一样的。
>
>   resReqResAmount：计划占用数量，是非独占资源的数量。非独占资源，比如场地可以好多个设备，这样它就不是独占资源。准确的讲，一个场地可以分配给多个任务进行工作。**场地类资源填写的是需要占用场地的面积**（整体有多大面积，需要多少面积）**知识类资源也是非独占资源，占用数量是用数字表示**。（知识类资源比如key，一个key可以给多台计算机使用，但key的总量是有限制的，跟场地面积异曲同工）
>
>   也就是计划占用时间，不论独占还是非独占都与工期一致；计划占用数量独占设置为 NULL，非独占填写占用面积。



人力资源：human

| 参数名  | 类型   | 备注                 |
| ------- | ------ | -------------------- |
| humUid  | String | 人力资源全局唯一编号 |
| humName | String | 人员姓名（非必须）   |

 

设备资源：equipment

| 参数名    | 类型   | 备注                 |
| --------- | ------ | -------------------- |
| equipUid  | String | 设备资源全局唯一编号 |
| equipName | String | 设备名称（非必须）   |

 

场地资源：place

| 参数名    | 类型    | 备注                 |
| --------- | ------- | -------------------- |
| placeUid  | String  | 场地资源全局唯一编号 |
| placeName | String  | 场地名称（非必须）   |
| placeArea | Integer | 场地总面积           |

 

知识资源：knowledge

| 参数名      | 类型    | 备注                   |
| ----------- | ------- | ---------------------- |
| knowlUid    | String  | 知识资源全局唯一编号   |
| knowlName   | String  | 知识资源名称（非必须） |
| knowlAmount | Integer | 该资源总数量           |

 

资源分配/占用：allocateResource

| 参数名              | 类型   | 备注                                                  |
| ------------------- | ------ | ----------------------------------------------------- |
| arUid               | String | 资源占用全局唯一编号                                  |
| arResUid            | String | 资源UID                                               |
| arResStartDateTime  | Long   | 资源占用开始时间（整体）                              |
| arResFinishDateTime | Long   | 资源占用结束时间（整体）                              |
| arResWork           | Float  | 资源占用时长（整体）                                  |
| arResAmount         | Float  | 资源占用数量（如果是独占资源，比如设备，直接设置为0） |

>   计划之前，资源已经被预订的数据。一个资源被一个任务预定了一段时间，就是一条数据。所有资源占用数据合在一起就是资源全局占用信息。
>
>   arResAmount：这个占用数量也就类似于之前，是非独占资源的面积或者知识类资源的数字。

 

节假日：holiday（字符串数组）

| 参数名 | 类型   | 备注                 |
| ------ | ------ | -------------------- |
|        | String | 日期格式"yyyy-MM-dd" |

 

#### 示例

完整示例：[北航流程优化算法输入示例.json](北航流程优化算法输入示例.json)

```json
{
	"project": [{
		"projUid": "313",
		"projName": "结构分系统初样",
		"projEarlyStartDateTime": 1577203200000,
		"projLateFinishDateTime": 1627272000000,
		"projPlanDur": 1600
	}...],
	"task": [{
		"taskUid": 4289,
		"taskName": "阶段任务准备",
		"taskType": 0,
		"taskProjUid": "313",
		"taskPriority": 0,
		"taskPlanDur": 8,
		"taskEarlyStartDateTime": 1577808000000,
		"taskLateFinishDateTime": 1578844800000,
		"taskWorkModel": 0
	}...],
  "taskGroup": [{
		"taskGroupUid": "56",
		"taskGroupTaskUid": 4310,
		"taskGroupGroupUid": "55"
	}...],
	"taskLink": [{
		"taskLinkPreTaskUid": 4289,
		"taskLinkSucTaskUid": 4290,
		"taskLinkType": 0
	}...],
	"holiday": ["2020-01-01", "2020-01-24", "2020-01-25", "2020-01-26", "2020-01-27", "2020-01-28", "2020-01-29", "2020-01-30", "2020-04-04", "2020-04-05", "2020-04-06", "2020-05-01", "2020-05-02", "2020-05-03", "2020-05-04", "2020-05-05", "2020-06-25", "2020-06-26", "2020-06-27", "2020-10-01", "2020-10-02", "2020-10-03", "2020-10-04", "2020-10-05", "2020-10-06", "2020-10-07", "2020-10-08"],
	"taskResPlan": [{
		"resPlanUid": "54_1435",
		"resPlanTaskUid": 4289,
		"resPlanPriority": 1
	}...],
	"taskResReq": [{
		"resReqUid": 188,
		"resReqResPlanUid": "54_1435",
		"resReqResType": 3,
		"resReqResUid": "37",
		"resReqResWork": 8,
		"resReqResAmount": 1,
		"resReqResWorkModel": 0
	}...],
	"allocateResource": [],
	"human": [{
		"humUid": "11",
		"humName": "产品经理"
	}...],
	"equipment": [{
		"equipUid": "24",
		"equipName": "五轴加工中心1-1"
	}...],
	"place": [{
		"placeUid": "8",
		"placeName": "噪声实验室1",
		"placeArea": 2
	}...],
	"knowledge": [{
		"knowlUid": "37",
		"knowlName": "adams许可证1",
		"knowlAmount": 2
	}...]
}
```



### 2.返回参数

```json
"nodeTask": [
    {
      "taskNo": "任务编号",
      "planStart": "任务计划开始时间(Long)",
      "planEnd": "任务计划结束时间(Long)",
      "planNo": "任务选择的资源方案的编号(String)",
      "resList": [{
        "resNo": "任务占用的资源的编号(String)",
        "planStart": "资源占用开始时间(Long)",
        "planEnd": "资源占用结束时间(Long)",
      }...]
    }
]
```


