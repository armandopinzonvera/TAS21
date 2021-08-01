**README**
Me toco hacer una minuta, para ver si logro avanzar
------------------------------------------------------------------------
> 4 / julio / 2021

	Objetivo: Pasar datos de localizacion de VistaTransecto a MapFragment
	Tecnica: He usado objeto bundle, y ahora ultimo cree un objeto bundle
		en el fragment para recibir,

	problema:  pero no recibe datos y marca null

	Considerar: Segun StackOverflow, se debe recibir en un objeto bundle,
		Revisar todo el foro..

	https://stackoverflow.com/search?q=data+from+activity+to+fragment
	https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
	https://stackoverflow.com/questions/30846973/getting-arguments-from-a-bundle
-----------------------------------------------------------------------------
	-- VVV --
> 11 / Julio /2021
	Objetivo: El mismo anterior
	Tecnica: Siguiendo comentario en StackOverflow
	>> Coloque el objeto bundle, con el putString() en el mismo metodo donde se abre el fragment:
		llenarWigets(),  para que este creado antes, y el metodo que envia los datos: sendLocationData()
		ejecuta el metodo de resivir en el fragment y coloca el setarguments()

	#: El problema ahora fue que no logro cojer datos de ubicacion

-----------------------------------------------------------------------------
	-- VVV --
> 14 / Julio /2021   > hoy si cojio datos de ubicacion, prendi el GPS antes de iniciar la aplicacion y no la reinstale
	esta con la ultima version






















