package Model.Athlete;

public class Competence extends Athlete {

    public Competence(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted, float weight, float height, double economy, String birthdate, Stats physicalsConditions) {
        super(number, name, last, nacionality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, physicalsConditions);
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
