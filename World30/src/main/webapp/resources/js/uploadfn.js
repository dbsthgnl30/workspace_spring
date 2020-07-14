/**
 * 
 */
  function getOriginalName(filename){

   if(checkImage(filename)){

    var idx = filename.indexOf("_");

    idx = filename.indexOf("_", idx+1);

    return filename.substring(idx+1);

   }else{

    var idx = filename.indexOf("_");

    return filename.substring(idx+1);

   }

  }



  function checkImage(filename){

   

   var idx = filename.lastIndexOf(".");



   var format = filename.substring(idx+1).toUpperCase();



   if(format == 'PNG' || format == 'JPG' || 

     format == 'JPEG' || format == 'GIF'){

    return true;

   }else{

    return false;

   }

  }

  

  function getImageLink(result){

   if(checkImage(result)){

    return result.substring(0,12) + result.substring(14);

   }else{

    return result;

   }

   

   

  }


 