const categoriaUrl = "http://localhost:8080/ActividadJavaServidor_war_exploded/categoria";
const peliculaUrl = "http://localhost:8080/ActividadJavaServidor_war_exploded/pelicula"

const llenadoCategoria = list => {
    let table = "";
    $('#categoriaTable > tbody').empty();

    if(list.lenght > 0){
        for (let i = 0; i < list.length; i++) {
            table += `
    <tr>
        <td>${i + 1}</td>
        <td>${list[i].nombre}</td>
    
        <td>
            <button title="Detalles" onclick="getInfoCategoria(${list[i].id});" type="button" class="btn btn-info" data-toggle="modal" data-target="#detailsEmployee"> <i class="fas fa-info"></i> </button>
        </td>
        <td>
            <button title="Editar" onclick="getInfoUpdateEmployee(${list[i].id});" type="button" class="btn btn-warning"  style="color: white;" data-toggle="modal" data-target="#updateEmployee"><i class="far fa-edit"></i></button>
        </td>
        <td>
            <button title="Eliminar" onclick="getEmployeeId(${list[i].id});" type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteEmployee" ><i class="fas fa-trash"></i></button>
        </td>

    </tr>
    `;
        }
    } else {
        table = `
<tr class="text-center">
    <td colspan="5">No hay registros para mostrar</td>
</tr>
`;
    }
    $('#categoriaTable > tbody').html(table);
}

const getCategorias = async () => {
    return await $.ajax({
        type: 'GET',
        url: categoriaUrl
    }).done(res => {
        let listCategorias = res;
        

        for(let i=0; i < Object.keys(listCategorias).length; i++){
            $('#categoriaTable').append('<tr>'+
            '<td align="center">' + res[i].nombre + '</td>' +
            '<td align="center">' + '<input type="hidden" id="" name="" value="' + res[i].id + ' ">' + '<button title="Detalles" class="btn btn-primary" onclick="getInfoCategoria('+ res[i].id +')" data-toggle="modal" data-target="#detailsCategoria" ><i class="fas fa-info"></i>' + '</td>' +
            '<td align="center">' + '<input type="hidden" id="" name="" value="' + res[i].id + ' ">' + '<button title="Editar" class="btn btn-warning" onclick="getInfoUpdateCategoria('+ res[i].id +')" data-toggle="modal" data-target="#updateCategoria" ><i class="fas fa-edit"></i></button>' + '</td>' +
            '<td align="center">' + '<input type="hidden" id="" name="" value="' + res[i].id + ' ">' + '<button title="Eliminar" class="btn btn-danger" onclick="deleteCategoria('+ res[i].id +')" data-toggle="modal" data-target="#deleteCategoria"><i class="fas fa-times"></i></button>' + '</td>' + '</tr>'+'<br>')
        }
    });
}

const getPeliculas = async () => {
    return await $.ajax({
        type: 'GET',
        url: peliculaUrl
    }).done(res => {
        let listPelicula = res;
        

        for(let i=0; i < Object.keys(listPelicula).length; i++){
            $('#peliculaTable').append('<tr>'+
            '<td align="center">' + res[i].título + '</td>' +
            '<td align="center">' + res[i].sinopsis+ '</td>' +
            '<td align="center">' + res[i].rating+ '</td>' +
            '<td align="center">' + res[i].fecha_de_Registro+ '</td>' +
            '<td align="center">' + res[i].fecha_de_Actualización+ '</td>' +
            '<td align="center">' + '<input type="hidden" id="" name="" value="' + res[i].id + ' ">' + '<button title="Detalles" class="btn btn-primary" onclick="getInfoPelicula('+ res[i].id +')" data-toggle="modal" data-target="#detailsPelicula" ><i class="fas fa-info"></i>' + '</td>' +
            '<td align="center">' + '<input type="hidden" id="" name="" value="' + res[i].id + ' ">' + '<button title="Editar" class="btn btn-warning" onclick="getInfoUpdatePelicula('+ res[i].id +')" data-toggle="modal" data-target="#updatePelicula" ><i class="fas fa-edit"></i></button>' + '</td>' +
            '<td align="center">' + '<input type="hidden" id="" name="" value="' + res[i].id + ' ">' + '<button title="Eliminar" class="btn btn-danger" onclick="deletePelicula('+ res[i].id +')" data-toggle="modal" data-target="#deletePelicula"><i class="fas fa-times"></i></button>' + '</td>' + '</tr>'+'<br>')
        }
    });
}

const getCategoriaId = async (id) => {
    return await $.ajax ({
        type: 'GET',
        url: categoriaUrl+'/'+id
    }).done (res => res);
}

const getPeliculaId = async (id) => {
    return await $.ajax ({
        type: 'GET',
        url: peliculaUrl+'/'+id
    }).done (res => res);
}

const getInfoCategoria = async (id) => {
    let categoria = await getCategoriaId(id);
    document.getElementById('nameDetails').value = categoria.nombre;
    console.log (res);
}

const getInfoPelicula = async (id) => {
    let pelicula = await getPeliculaId(id);
    document.getElementById('tituloDetails').value = pelicula.título;
    document.getElementById('descripcionDetails').value = pelicula.descripción;
    document.getElementById('sinopsisDetails').value = pelicula.sinopsis;
    document.getElementById('ratingDetails').value = pelicula.rating;
    document.getElementById('registerDetails').value = pelicula.fecha_de_Registro;
    document.getElementById('updateDetails').value = pelicula.fecha_de_Actualización;
    document.getElementById('categoriaDetails').value = pelicula.categoría;
    document.getElementById('statusDetails').value = pelicula.estado? "Activo": "Inactivo";
    console.log (res);
}

const getInfoUpdateCategoria = async (id) => {
    let categoria = await getCategoriaId(id);
    document.getElementById('idCategoriaUpdate').value = id;
    document.getElementById('nameUpdate').value = categoria.nombre;
}

const getInfoUpdatePelicula = async (id) => {
    let pelicula = await getPeliculaId(id);
    document.getElementById('idPeliculaUpdate').value = id;
    document.getElementById('tituloUpdate').value = pelicula.título;
    document.getElementById('descripcionUpdate').value = pelicula.descripción;
    document.getElementById('sinopsisUpdate').value = pelicula.sinopsis;
    document.getElementById('ratingUpdate').value = pelicula.rating;
    document.getElementById('registerUpdate').value = pelicula.fecha_de_Registro;
    document.getElementById('categoriaUpdate').value = pelicula.categoría;
   
}

const getIDCategoria = async (id) => {
    document.getElementById('idCategoriaDelete').value = id;
}

const getIDPelicula = async (id) => {
    document.getElementById('idPeliculaDelete').value = id;
}

const registerPelicula = async () => {
    let título = document.getElementById('tituloCreate').value;
    let descripción = document.getElementById('descripcionCreate').value;
    let sinopsis = document.getElementById('sinopsisCreate').value;
    let rating = document.getElementById('ratingCreate').value;
    let fecha_de_Registro = document.getElementById('registerCreate').value;
    let categoría = document.getElementById('categoriaCreate').value;

    let object = {título,descripción,sinopsis,rating,fecha_de_Registro,categoría};
    console.log(object);

    await $.ajax({
        type: 'POST',
        url: peliculaUrl+'/create',
        data: object
    }).done(function(res){
        console.log(res);
    });
}

const registerCategoria = async () => {
    let nombre = document.getElementById('nameCreate').value;
    await $.ajax({
        type: 'POST',
        url: categoriaUrl+'/create',
        data: nombre
    }).done(function(res){
        console.log(res);
    });
}

const updateCategoria = async () => {
    
    let id = document.getElementById('idCategoriaUpdate').value;
    let nombre =  document.getElementById('nameUpdate').value;

    let object = {id,nombre};
    await $.ajax({
        type: 'POST',
        url: categoriaUrl+'/update/'+id,
        data: object
    }).done(function(res){
        console.log(res);
    });

}

const updatePelicula = async () => {
    let id = document.getElementById('idPeliculaUpdate').value;
    let título = document.getElementById('tituloUpdate').value;
    let descripción = document.getElementById('descripcionUpdate').value;
    let sinopsis = document.getElementById('sinopsisUpdate').value;
    let rating = document.getElementById('ratingUpdate').value;
    let fecha_de_Registro = document.getElementById('registerUpdate').value;
    let categoría = document.getElementById('categoriaUpdate').value;

    let object = {título,descripción,sinopsis,rating,fecha_de_Registro,categoría};
    console.log(object);

    await $.ajax({
        type: 'POST',
        url: peliculaUrl+'/update+/'+id,
        data: object
    }).done(function(res){
        console.log(res);
    });
    
}

const deleteCategoria = async () => {
    let id = document.getElementById('idCategoriaDelete').value;
    await $.ajax({
        type: 'POST',
        url: categoriaUrl+'/delete/'+id
    }).done(res => {
        console.log(res);
    })
}

const deletePelicula = async () => {
    let id = document.getElementById('idPeliculaDelete').value;
    await $.ajax({
        type: 'POST',
        url: categoriaUrl+'/delete/'+id
    }).done(res => {
        console.log(res);
    })
}

getCategorias();
getPeliculas();