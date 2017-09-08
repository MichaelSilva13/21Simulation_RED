package evenement;

/**
 *
 * Classe abstraite représentant un évènement du système d'évènements discret
 * @author _Cedryk_
 *
 */
public abstract class Event{
	
	private int time;
	
	/**
	 * Crée un évènement en spécifiant son temps
	 * @param time temps auquel démarrer l'évènement
	 */
	public Event(int time){
		this.time = time;
	}
	
	/**
	 * Démarre l'évènement
	 */
	public abstract void run();
	
	/**
	 * Retourne le temps de l'évènement
	 * @return le temps de l'évènement
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * @param time Le temps auquel l'évènement sera déclenché
	 */
	public void setTime(int time){
		this.time= time;
	}
	
	/**
	 * Retourne le type d'évènement et le temps de l'évènement sous forme de texte
	 * @return le type d'évènement et le temps de l'évènement sous forme textuelle
	 */
	public String toString() {
		return this.getClass().getName() +" : "+ this.getTime();

	}
	
}
