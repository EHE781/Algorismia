n es passat com a paràmetre (mida tauler);
posicioX, posicioY passat com a paràmetre (posició cavall);
Begin
	movX : arrayEnters(8);
	movY : arrayEnters(8);
	tauler : arrayArrayEnters(n)(n);
	cavall : enter;
	solucions : enter;
	
	Begin
		solucions := 0;
		cavall := 1;
		for i:arrayEnters in tauler do begin
			for j:enter in i do begin
				j := 0;
			end;
		end;
		tauler(posicioX)(posicioY) := cavall;
		cavall := cavall + 1;
		if (posicioX >= 0) and (posicioX) <= n and (posicioY >= 0) and (posicioY) <= n then begin
			moureCavall(posicioX, posicioY);
		end;
	End;
	
	function moureCavall(x:enter, y:enter) begin
		fila, columna : enter;
		for(i:enter; i<8; i:=i+1) do begin
			filaColumna : enter;
			filaColumna := seguentMoviment(x, y, i);
			if filaColumna /= -1 then begin
				fila := filaColumna / (n+1);
				columna := filaColumna % (n+1);
				tauler(fila)(columna) := cavall;
				cavall := cavall + 1;
				solucio : booleana;
				solucio := moureCavallRecursiu(fila, columna);
				if (solucio = true) and (solucions < 3) then begin
					for j : arrayEnters in tauler do begin
						for k : enter in j do begin
							imprimir(k);
							imprimir(" ");
						end;
						imprimirLinia("");
					end;				
				
					for fila : arrayEnters in tauler do begin
						for columna : enter in fila do begin
							fila(columna) := 0;
						end;
					end;
					cavall := 1;
					tauler(posicioX)(posicioY) := cavall;
					cavall := cavall + 1;
					solucions := solucions + 1;
					
					if solucions = 3 then begin
						sortir; //instrucció per sortir del for
					end;
				end;
			end;
		end;
	end;

	function booleana moureCavallRecursiu(x:enter, y:enter) begin
		fila, columna : enter;
		for(i:enter; i<8; i:=i+1) do begin
			filaColumna : enter;
			filaColumna := seguentMoviment(x, y, i);
			if filaColumna /= -1 then begin
				fila := filaColumna / (n+1);
				columna := filaColumna % (n+1);
				tauler(fila)(columna) := cavall;
				if (cavall = n*n) then begin
					return true;
				end;
				enrere : booleana;
				enrere := moureCavallRecursiu(fila, columna);
				if enrere = true then begin
					return true;
				end;
				tauler(fila)(columna) := 0;
				cavall := cavall - 1;
			end;
		end;
		return false;
	end;
	
	function seguentMoviment(x:enter, y:enter, iteracio:enter) begin
		fila, columna : enter;
		fila := fila + movX(iteracio);
		columna := columna + movY(iteracio);
		if (fila >= 0) and (fila <= n) and (columna >= 0) and (columna <= n) then begin
			return fila * n+1 + columna;
		else
			return -1;
		end;
	end;
	
	function iniciarMov() begin
		movX(0) := -2;
        movY(0) := 1;
        movX(1) := -1;
        movY(1) := 2;
        movX(2) := 1;
        movY(2) := 2;
        movX(3) := 2;
        movY(3) := 1;
        movX(4) := 2;
        movY(4) := -1;
        movX(5) := 1;
        movY(5) := -2;
        movX(6) := -1;
        movY(6) := -2;
        movX(7) := -2;
        movY(7) := -1;
	end;
End;
