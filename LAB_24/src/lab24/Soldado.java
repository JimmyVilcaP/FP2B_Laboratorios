package lab24;

import java.io.Serializable;

public abstract class Soldado implements Serializable  {
	private static final long serialVersionUID = 3L;
    protected String nombre;
    protected int nivelAtaque, nivelDefensa, nivelVida, vidaActual, velocidad, id;
    protected boolean vive;
    protected int ejercitoID;
    
    private int fila, columna;
	private Actitud actitud;
    public enum Actitud {
        DEFENSIVA, OFENSIVA, HUIDA
    }

    public Soldado() {
        this.vive = false;
    }

    public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int nivelVida, int id, boolean vive, int ejercitoID) {
        this.nombre = nombre;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        this.nivelVida = nivelVida;
        this.vidaActual = nivelVida;
        this.vive = vive;
        this.velocidad = 1;
        this.id = id;
        this.ejercitoID = ejercitoID;
        this.actitud = Actitud.DEFENSIVA;
    }
    public abstract void atacar(Soldado enemigo);
    public abstract void defender();
    
    public void huir() {
        if (!vive) return;
        actitud = Actitud.HUIDA;
        velocidad += 2;
    }

    public void retroceder() {
        if (!vive) return;
        if (velocidad > 0) {
            defender();
        } else {
            velocidad--;
        }
    }

    public void serAtacado(int ataque) {
        if (!vive) return;
        int danio = ataque - this.nivelDefensa;
        vidaActual -= Math.max(danio, 0); 
        if (vidaActual <= 0) {
            morir();
        }
    }

    public void avanzar() {
        velocidad++;
    }

    public void morir() {
        vive = false;
        actitud = null;
        velocidad = 0;
    }

    // Getters y Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }

    public int getAtaque() {
        return nivelAtaque;
    }
    
    public void setDefensa(int nivelDefensa) {
        this.nivelDefensa = nivelDefensa;
    }

    public int getDefensa() {
        return nivelDefensa;
    }
    
    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
        if (vidaActual <= 0) {
            morir();
        }
    }

    public int getVidaActual() {
        return vidaActual;
    }
    
    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getFila() {
        return fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getColumna() {
        return columna;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Actitud getActitud() {
        return actitud;
    }

    public int getId() {
        return id;
    }
    
    public int getEjercitoId() {
        return ejercitoID;
    }

    public boolean estaVivo() {
        return vive;
    }
    public void defenderS() {
        if (!vive) return;
        actitud = Actitud.DEFENSIVA;
        velocidad = 0;
    }

    // Método para mostrar datos
    public String mostrarREF() {
        String tipoInicial;
        if (this instanceof Espadachin) {
            tipoInicial = "E"; // Espadachín
        } else if (this instanceof Caballero) {
            tipoInicial = "C"; // Caballero
        } else if (this instanceof Arquero) {
            tipoInicial = "A"; // Arquero
        } else if (this instanceof Lancero) {
        	tipoInicial = "L";
        } else {
            tipoInicial = "X";
        }
        return "R" + ejercitoID + tipoInicial + id;
    }

    @Override
    public String toString() {
        return this.mostrarREF() 
        		+ " | " + this.getNombre()
        		+ " | " + this.getVidaActual();
    }

    public void setActitud(Actitud actitud) {
        this.actitud = actitud;
    }
 // En la clase Soldado
    public void afectarVidaPorReglas(Soldado soldadoEnemigo) {
        // Si el soldado es una unidad especial, aplicamos las reglas específicas
        if (this instanceof EspadachinReal || this instanceof CaballeroFranco || 
            this instanceof EspadachinTeutonico || this instanceof EspadachinConquistador || 
            this instanceof CaballeroMoro) {
            
            // Regla Caballero vs Arquero
            if (soldadoEnemigo instanceof Arquero) {
                if (this instanceof CaballeroFranco) { // Caballero Franco especial
                    this.setVidaActual(this.getVidaActual() + 2); // Aumenta 2
                } else {
                    this.setVidaActual(this.getVidaActual() + 1); // Aumenta 1
                }
            }

            // Regla Caballero vs Lancero
            if (soldadoEnemigo instanceof Lancero) {
                if (!(this instanceof CaballeroFranco)) { 
                    soldadoEnemigo.setVidaActual(soldadoEnemigo.getVidaActual() + 1);
                }
            }

            // Regla Arquero vs Lancero
            if (soldadoEnemigo instanceof Lancero) {
                this.setVidaActual(this.getVidaActual() + 1);
            }

            // Regla Caballero vs Espadachín
            if (soldadoEnemigo instanceof Espadachin) {
                this.setVidaActual(this.getVidaActual() + 1);
            }

            // Regla Espadachín vs Lancero
            if (soldadoEnemigo instanceof Lancero) {
                if (this instanceof EspadachinReal || this instanceof EspadachinConquistador) {
                    this.setVidaActual(this.getVidaActual() + 2);
                } else {
                    this.setVidaActual(this.getVidaActual() + 1);
                }
            }

            // Regla Espadachín vs Espadachín especial
            if (soldadoEnemigo instanceof EspadachinReal || soldadoEnemigo instanceof EspadachinConquistador) {
                if (this instanceof Espadachin) {
                    this.setVidaActual(this.getVidaActual() + 1); 
                }
            }

            // Regla Caballero vs Caballero especial
            if (soldadoEnemigo instanceof CaballeroFranco || soldadoEnemigo instanceof CaballeroMoro) {
                if (this instanceof Caballero) {
                    this.setVidaActual(this.getVidaActual() + 1); 
                }
            }
        }
    }
}