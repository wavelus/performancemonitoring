public class Presenter {
    private MyString myString;
    private View view;

    public Presenter(View view, MyString myString){
        this.view = view;
        this.myString = myString;
    }

    public void action(String string){

        if(string.equals("String One")){
            myString = new StringOne();
            view.jTextField.setText(""+ (view.stopTime - view.startTime));
        }

        if(string.equals("String Two")){
            myString = new StringTwo();
            view.jTextField.setText(myString.changeMyString());
        }

    }
}
