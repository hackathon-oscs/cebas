// JavaScript Document
function mascara(o, f) {  
	v_obj = o;  
	v_fun = f;  
	setTimeout("execmascara()", 1);  
}  
	
function execmascara() {  
	v_obj.value = v_fun(v_obj.value);  
}  
	
function valor(v) {  
	v = v.replace(/\D/g, "");  
	v = v.replace(/[0-9]{15}/, "invalido");  
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2"); // coloca ponto antes dos últimos 11 digitos  
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2"); // coloca ponto antes dos últimos 8 digitos  
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2"); // coloca ponto antes dos últimos 5 digitos  
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2"); // coloca virgula antes dos últimos 2 digitos  
	return v; 
}

function somentenumero(v){
	v = v.replace(/\D/g, "");  
	return v; 
}

function valida(o){
	v_obj = o;
	if(!valida_cpf(v_obj.value))
		alert('CPF Inválido');
}

function valida_cpf(cpf){
    var numeros, digitos, soma, i, resultado, digitos_iguais;
    digitos_iguais = 1;
    if (cpf.length < 11)
          return false;
    for (i = 0; i < cpf.length - 1; i++)
          if (cpf.charAt(i) != cpf.charAt(i + 1))
                {
                digitos_iguais = 0;
                break;
                }
    if (!digitos_iguais)
          {
          numeros = cpf.substring(0,9);
          digitos = cpf.substring(9);
          soma = 0;
          for (i = 10; i > 1; i--)
                soma += numeros.charAt(10 - i) * i;
          resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
          if (resultado != digitos.charAt(0))
                return false;
          numeros = cpf.substring(0,10);
          soma = 0;
          for (i = 11; i > 1; i--)
                soma += numeros.charAt(11 - i) * i;
          resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
          if (resultado != digitos.charAt(1))
                return false;
          return true;
          }
    else
          return false;
}

function FecharJanela() 
{
	ww = window.open(window.location, "_self");
	ww.close();
} 

/**
 * Função para abrir e fechar o menu principal do modo mobile.
 * Se ele estiver escondido, o abre, senão o fecha.
 * @param abrir {para forçar a abertura ou fechamento}
 */
function abrirFecharMenu(abrir) {
	var objMenu = document.getElementById('menu');
	var objPelicula = document.getElementById('peliculaMenu');
	var classeCss;
	
	// se a variável abrir não estiver definida, utilizar valor de "class", interpolando entre abrir e fechar,
	// porém, se abrir estiver definido, é para forçar a bertura ou fechamento do menu
	if (typeof(abrir) == 'undefined')
		classeCss = objMenu.className;
	else {
		// simula como se estivesse fechado, para poder abrir, e ao contrário no "else"
		if (abrir)
			classeCss = '';
		else
			classeCss = 'ativo';
	}
	
	if (classeCss == 'ativo') {
		objMenu.className = '';
		objPelicula.className = '';
	}
	else {
		objMenu.className = 'ativo';
		objPelicula.className = 'ativo';
	}
}