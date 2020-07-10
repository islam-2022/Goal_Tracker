var express = require('express');
var app = express();



app.get('/',function(req,res){
	res.send('Hello World!');
});

app.get('/test',function(req,res){
	res.send('you are in testing page!');
});

// listen to environment varibale port or 3000 otherwise.
var port = process.env.PORT || 3000;
app.listen(port, function(){
	console.log(`app listening on port ${port}...`);
});