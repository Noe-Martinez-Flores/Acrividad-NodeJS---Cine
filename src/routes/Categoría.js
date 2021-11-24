const express = require ('express');
const router = express.Router();
const pool = require('../database');

router.get('/',async(request,response)=>{
    let listCategorías = await pool.query('SELECT * FROM Categoría');

    response.json({
        status:200,
        message: "Listado Realizado",
        listCategorías: listCategorías
    });
});

router.get('/:id',async(request,response)=>{
    const {id} = request.params;
    let categoría = await pool.query('SELECT * FROM Categoría WHERE id = ?',[id]);

    response.json({
        status:200,
        message: "¡Categoría encontrada!",
        categoría: categoría
    });
});


router.post('/create',async(request,response)=>{
    const {Nombre} = request.body;
    const categoría = {Nombre}
    await pool.query('INSERT INTO Categoría SET ?',[categoría]);

    response.json({
        status:200,
        message: "¡Categoría Registrada!",
        categoría: categoría
    });
});

router.post('/update/:id',async(request,response)=>{
    const {id} = request.params;
    const {Nombre} = request.body;
    const categoría = {Nombre}
    await pool.query('UPDATE Categoría SET ? WHERE id = ?',[categoría,id]);

    response.json({
        status:200,
        message: "¡Categoría Actualizada!",
        categoría: categoría
    });
});

router.delete('/delete/:id',async(request,response)=>{
    const {id} = request.params;
    
    await pool.query('DELETE FROM Categoría WHERE id = ?',[id]);

    response.json({
        status:200,
        message: "¡Categoría Eliminada!",
        
    });
});

module.exports = router;