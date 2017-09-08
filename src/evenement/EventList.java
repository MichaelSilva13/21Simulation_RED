package evenement;

import java.util.LinkedList;

/**
 * Classe dérivant de LinkedList servant à gérer une liste d'évènements,
 * enregistrée sous forme de file
 *
 * @author _cedryk_
 *
 *
 */
public class EventList extends LinkedList<Event> {

	private static final long serialVersionUID = -7228753528420143743L;

	/**
	 * Instancie la liste comme une LinkedList normale (liste vide)
	 */
	public EventList() {
		super();
	}

	/**
	 * Ajoute un évènement à la liste en fonction du temps de l'évènement. La
	 * liste est donc constamment en ordre croissant de temps.
	 *
	 * @param event l'évènement à ajouter
	 * @return true si l'évènement a été ajouté et false si non
	 */
	public boolean addEvent(Event event) {
		double timeBefore = 0;
		Event currEvent;
		for (int i = 0; i < this.size(); i++) {
			currEvent = this.get(i);
			if (event.getTime() < currEvent.getTime() && event.getTime() >= timeBefore) {
				this.add(i, event);
				return true;
			}
			timeBefore = currEvent.getTime();
		}
		if (this.size() == 0) {
			this.addFirst(event);
			return true;
		} else if (event.getTime() >= this.getLast().getTime()) {
			this.addLast(event);
			return true;
		}

		return false;
		 
	}

}
