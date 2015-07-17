contructor<entity>:public ${dstClazzName}(${orignClazzName} ${orignClazzName?uncap_first}){

common:
this.${fieldName} = ${origInstanceName}.get${fieldName?cap_first}();

entity:
if(${origInstanceName}.get${fieldName?cap_first}() != null){
    this.${fieldName} = new ${destObjName}(${origInstanceName}.get${fieldName?cap_first}());
}

List<entity>:
if(${origInstanceName}.get${fieldName?cap_first}() != null){
    this.${fieldName} = new ArrayList<${destObjName}>();
    for(${origObjName} ${origObjName?uncap_first} : ${origInstanceName}.get${fieldName?cap_first}()){
        this.${fieldName}.add(new ${destObjName}(${origObjName?uncap_first}));
    }
}

Set<entity>:
if(${origEntityName}.get${fieldName?cap_first}() != null){
	this.${fieldName} = new HashSet<${destObjName}>();
	for(${origObjName} ${origObjName?uncap_first} : ${origEntityName}.get${fieldName?cap_first}()){
	    this.${fieldName}.add(new ${destObjName}(${origObjName?uncap_first}));
	}
}



