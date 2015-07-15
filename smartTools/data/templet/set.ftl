common:
this.set${fieldName?cap_first}(${origInstanceName}.get${fieldName?cap_first}());


entity:
if(${origInstanceName}.get${fieldName?cap_first}() != null){
	this.set${fieldName?cap_first}(new ${destObjName}(${origInstanceName}.get${fieldName?cap_first}()));
}

List<entity>:
if(${origInstanceName}.get${fieldName?cap_first}() != null){
	List<(${destObjName}> list = new ArrayList<${destObjName}>();
	for(${origObjName} ${origObjName?uncap_first} : ${origInstanceName}.get${fieldName?cap_first}()){
		list.add(new ${destObjName}(${origObjInstance}));
	}
	this.set${fieldName?cap_first}(list);
}
 
Set<entity>:
if(${origEntityName}.get${fieldName?cap_first}() != null){
	Set<(${destObjName}> set = new HashSet<${destObjName}>();
	for(${origObjName} ${origObjName?uncap_first} : ${origEntityName}.get${fieldName?cap_first}()){
		set.add(new ${destObjName}(${origObjName?uncap_first}));
	}
	this.set${fieldName?cap_first}(set);
}



