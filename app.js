var express = require('express');
var app = express();



app.get('/',function(req,res){
	res.send('Hello World!');
});

// listen to environment varibale port or 3000 otherwise.
var port = process.env.PORT || 3000;
app.listen(port, function(){
	console.log(`app listening on port ${port}...`);
});