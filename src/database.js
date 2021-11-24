const mysql = require('mysql');
const {promisify} = require('util');
const {database} = require('./keys');

const pool = mysql.createPool(database);

pool.getConnection((error,connection) => {
    if(error){
        if(error.code == 'PROTOCOL_CONNECTION_LOST'){
            console.log("DATABASE WAS CLOSED");

        }
        if(error.code == 'ER_CON_COUNT_ERROR'){
            console.log("DATABASE WAS TO MANY CONNNECTIONS");
        }
        if(error.code == 'ECONNREFUSED'){
            console.log("DATABASE CONNECTION REFUSED");
        }
    }

    if(connection) connection.release();
    console.log("DATABASE IS CONNECTED");
    return
});

pool.query = promisify(pool.query);
module.exports = pool;