const express = require ('express');
const router = express.Router();
const pool = require('../database');

router.get('/',async(request,response)=>{
    let listPelículas = await pool.query('SELECT * FROM Película');

    response.json({
        status:200,
        message: "Listado Realizado",
        listPelículas: listPelículas
    });
});

router.get('/:id',async(request,response)=>{
    const {id} = request.params;
    let película = await pool.query('SELECT * FROM Película WHERE id = ?',[id]);

    response.json({
        status:200,
        message: "¡Película encontrada!",
        película: película
    });
});


router.post('/create',async(request,response)=>{
    const {Título,Descripción,Sinopsis,Rating,Fecha_de_Registro,Categoría} = request.body;
    const película = {Título,Descripción,Sinopsis,Rating,Fecha_de_Registro,Categoría}
    const date = request.body;
    const status = request.body;

    var fecha = new Date();
    var month = fecha.getUTCMonth()+1;
    var day = fecha.getUTCDate();
    var year = fecha.getUTCFullYear();

    actualización = year+"-"+month+"-"+day
    date.Fecha_de_Acrualización = actualización;
    Fecha_de_Acrualización = date.Fecha_de_Acrualización;

    status.Estado = "1";
    Estado = status.Estado;
    await pool.query('INSERT INTO Película SET ?, Fecha_de_Actualización = ?, Estado = ?',[película,Fecha_de_Acrualización,Estado]);

    response.json({
        status:200,
        message: "¡Película Registrada!",
        película: película
    });
});

router.post('/update/:id',async(request,response)=>{
    const {id} = request.params;
    const {Título,Descripción,Sinopsis,Rating,Categoría} = request.body;
    const película = {Título,Descripción,Sinopsis,Rating,Categoría}

    const date = request.body;
    

    var fecha = new Date();
    var month = fecha.getUTCMonth()+1;
    var day = fecha.getUTCDate();
    var year = fecha.getUTCFullYear();

    actualización = year+"-"+month+"-"+day
    date.Fecha_de_Acrualización = actualización;
    Fecha_de_Acrualización = date.Fecha_de_Acrualización;


    await pool.query('UPDATE Película SET ?, Fecha_de_Actualización = ? WHERE id = ?',[película,Fecha_de_Acrualización,id]);

    

    response.json({
        status:200,
        message: "¡Película Actualizada!",
        película: película
    });
});

router.delete('/delete/:id',async(request,response)=>{
    const {id} = request.params;
    
    await pool.query('DELETE FROM Película WHERE id = ?',[id]);

    response.json({
        status:200,
        message: "¡Película Eliminada!",
        
    });
});

module.exports = router;