import java.util.*;

public class Liga {
    private List<Equipo> equipos;
    private String nombre;

    public Liga(String nombre) {
        super();
        this.equipos = new ArrayList<>();
        this.nombre = nombre;
    }

    /**
     * Este metodo se encarga de añadir un equipo a la liga, si el equipo ya está en la liga
     * lanzamos una excepción, si el equipo no tiene todos los jugadores lanzamos una excepción.
     *
     * @param e
     * @throws LigaException
     */
    public void addEquipo(Equipo e) throws LigaException {

        //Si el equipo ya está en la liga lanzamos una excepción.
        if (equipos.contains(e)) {
            throw new LigaException("El equipo ya está en la liga");
        }
        //Si el equipo no tiene todos los jugadores lanzamos una excepción.
        if (e.getJugadores().isEmpty()) {
            throw new LigaException("El equipo no tiene todos los jugadores");
        }
        //añadimos el equipo a la liga.
        equipos.add(e);
    }

    /**
     * Este método se encarga de eliminar un equipo de la liga, si el equipo no está en la liga
     * lanzamos una excepción.
     *
     * @param e
     * @throws LigaException
     */
    public void eliminaEquipo(Equipo e) throws LigaException {
        //Comprobamos que el equipo se encuentra en la liga, si no está lanzamos una excepción.
        if (!equipos.contains(e)) {
            throw new LigaException("El equipo no está en la liga");
        }
        //Eliminamos el equipo de la liga.
        equipos.remove(e);
    }

    /**
     * Este método se encarga de unir el equipo e2 con el e1 siendo este último el que se queda con los jugadores.
     *
     * @param e1
     * @param e2
     * @throws LigaException
     */
    public void unirEquipos(Equipo e1, Equipo e2) throws LigaException {

        if(!(equipos.contains(e1) && equipos.contains(e2))) {
            throw new LigaException("Uno de los dos equipos introducidos no se encuentra en la liga.");
        }
        e1.getJugadores().addAll(e2.getJugadores());
    }

    /**
     * Este método se encarga de devolver una lista con todos los jugadores en común de los dos equipos
     * que se le pasan por parámetro.
     *
     * @return List<Jugador>
     * @throws LigaException
     */
    public List<Jugador> jugadoresEnComun(Equipo e1, Equipo e2) throws LigaException {
        //Creamos una lista de jugadores en común.
        List<Jugador> jugadoresEnComun = new ArrayList<>();
        //Si no contiene e1 o e2 lanzamos una excepción.
        if (!equipos.contains(e1) && equipos.contains(e2)) {
            throw new LigaException("Uno de los dos equipos introducidos no se encuentra en la liga.");
        }
        /*
         * Recorremos la lista de jugadores del equipo 1 y comprobamos si el equipo 2 contiene el jugador.
         * Si lo contiene lo añadimos a la lista de jugadores en común.
         */
        for (Jugador jugador : e1.getJugadores()) {
            //
            if (e2.getJugadores().contains(jugador)) {
                jugadoresEnComun.add(jugador);
            }
        }
        //Devolvemos la lista de jugadores en común.
        return jugadoresEnComun;
    }

    /**
     * Este método se encarga de devolve la media de la edad de todos los jugadores de todos los equipos
     *
     * @return double
     * @throws LigaException
     */
    public double mediaEdad() throws LigaException {

        //Si no hay jugadores en la liga lanzamos una excepción.
        if (todosLosJugadores().isEmpty()) {
            throw new LigaException("No existe jugadores.");
        }
        //Calculamos la media de edad de los jugadores de la liga.
        return todosLosJugadores().stream().mapToInt(j -> j.getEdad()).average().orElseThrow(LigaException::new);
    }

    /**
     * Este método se encarga de devolver una lista con todos los jugadores ordenados por edad.
     *
     * @return List<Jugador>
     */
    public List<Jugador> jugadoresOrdenadosEdad() {

        List<Jugador> jugadoresOrdenadosEdad = new ArrayList<>(todosLosJugadores());

        /*
         * Clase anónima que nos ordena la lista de todos los jugadores por la edad.
         */
        jugadoresOrdenadosEdad.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador o1, Jugador o2) {
                //Ordenamos la lista por edad.
                return o2.getEdad() - o1.getEdad();
            }
        });
        return jugadoresOrdenadosEdad;
    }

    /**
     * Este método se encarga de devolver una lista con todos los jugadores ordenados por nombre.
     *
     * @return List<Jugador>
     */
    public List<Jugador> jugadoresOrdenadosNombre() {

        List<Jugador> jugadoresOrdenadosNombre = new ArrayList<>(todosLosJugadores());

        /*
         * Clase anónima que nos ordena la lista de todos los jugadores por el nombre.
         */
        jugadoresOrdenadosNombre.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador o1, Jugador o2) {
                //Ordenamos la lista por nombre.
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });

        return jugadoresOrdenadosNombre;
    }

    /**
     * Este método se encarga de devolver un conjunto de todos los jugadores de todos los equipos
     * de la liga pero sin repetir, ya que los conjuntos no permiten elementos repetidos.
     *
     * @return Set<Jugador>
     */
    private Set<Jugador> todosLosJugadores() {

        //Añadir todos los jugadores de los equipos a un nuevo set
        Set<Jugador> todosLosJugadores = new HashSet<>();

        Iterator<Equipo> it = equipos.iterator();

        while (it.hasNext()) {
            todosLosJugadores.addAll(it.next().getJugadores());
        }


        return todosLosJugadores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Bienvenidos a " + nombre).append(System.lineSeparator());
        sb.append("Equipos: ").append(System.lineSeparator());
        for (Equipo e : equipos) {
            sb.append(e).append(System.lineSeparator());
        }

        return sb.toString();

    }
}
