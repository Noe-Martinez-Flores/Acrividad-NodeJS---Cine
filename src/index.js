const express = require ('express');
const morgan = require ('morgan');

const app = express();

app.set('port',process.env.PORT || 4000);

app.use(morgan('dev'));
app.use(express.urlencoded({extended:false}));
app.use(express.json());

app.use(require('./routes/index'));
app.use('/categoria',require('./routes/Categoría'));
app.use('/pelicula',require('./routes/Película'));

app.listen(app.get('port'),() => {
    console.log("Servidor en el puerto #",app.get('port'))
});