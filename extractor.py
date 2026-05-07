import os

def extraer_todo_el_proyecto():
    # Nombre del archivo de salida
    archivo_final = "PROYECTO_COMPLETO_CONTEXTO.txt"
    
    # Extensiones que nos interesan para el análisis
    extensiones_permitidas = ('.java', '.properties', '.xml', '.yml', '.yaml', '.sql')
    
    # Carpetas que NO queremos (basura o binarios)
    excluir = {'target', '.idea', '.git', '.mvn', 'data', '__pycache__'}

    print(f"--- Iniciando extracción de archivos en {os.getcwd()} ---")

    with open(archivo_final, "w", encoding="utf-8") as destino:
        for raiz, dirs, archivos in os.walk(os.getcwd()):
            # Filtrar directorios excluidos
            dirs[:] = [d for d in dirs if d not in excluir]
            
            for nombre_archivo in archivos:
                if nombre_archivo.endswith(extensiones_permitidas):
                    ruta_completa = os.path.join(raiz, nombre_archivo)
                    ruta_relativa = os.path.relpath(ruta_completa, os.getcwd())
                    
                    destino.write("\n" + "="*80 + "\n")
                    destino.write(f"RUTA DEL ARCHIVO: {ruta_relativa}\n")
                    destino.write("="*80 + "\n\n")
                    
                    try:
                        with open(ruta_completa, "r", encoding="utf-8") as f_origen:
                            contenido = f_origen.read()
                            destino.write(contenido)
                    except Exception as e:
                        destino.write(f"ERROR: No se pudo leer este archivo. {e}")
                    
                    destino.write("\n\n")
                    print(f"Añadido: {ruta_relativa}")

    print(f"\n--- LISTO! ---")
    print(f"Todo tu proyecto ha sido volcado en: {archivo_final}")

if __name__ == "__main__":
    extraer_todo_el_proyecto()