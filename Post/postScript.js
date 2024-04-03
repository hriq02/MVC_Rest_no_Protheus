const mvc = "MvcCApiM";
const form = "FORMZI2";
const opert = 3;

const items = {
	Campos: "items",
}

function Formatador(jsonIn){
	let jsonReturn = {id:mvc, operation:opert, models:[{ id:form, modeltype:"FIELDS", fields:[]}]};
	let index = 0;
	let fieldsArray = []
	for (item in jsonIn){
		index++;
		const field = {id:item, order:index, value:jsonIn[item]};
		fieldsArray.push(field);
	}
	jsonReturn.models.fields = fieldsArray;

	return {id:mvc, operation:opert, models:[{ id:form, modeltype:"FIELDS", fields:fieldsArray}]};
}


let data = JSON.stringify(Formatador(items));


fetch(url,{
	method: "POST",
	headers:{
		'Content-Type':'application/json'
	},
	body: data
} ).then(res => res.json()).then(datart => console.log(JSON.stringify(datart)));
