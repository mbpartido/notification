import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Notificationmanager extends CordovaPlugin {

	public static final String TAG = "Notification Manager";
	/**
	* Constructor.
	*/
	public Notificationmanager() {}
	/**
	* Sets the context of the Command. This can then be used to do things like
	* get file paths associated with the Activity.
	*
	* @param cordova The context of the main Activity.
	* @param webView The CordovaWebView Cordova is running in.
	*/
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v(TAG,"Init NotificationManager");
	}

	public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		// Variables de la notificacion
    	NotificationManager nm;
    	Notification notif;
    	static String ns = Context.NOTIFICATION_SERVICE;
     
		//Defino los iconos de la notificacion en la barra de notificacion
    	int icono_v = R.drawable.n_icon2;
    	int icono_r = R.drawable.n_icon3;

		final int duration = Toast.LENGTH_SHORT;
		// Shows a toast
		Log.v(TAG,"NotificationManager received:"+ action);
		cordova.getActivity().runOnUiThread(new Runnable() {
			public void run() {
				// Inicio el servicio de notificaciones accediendo al servicio
    			nm = (NotificationManager) getSystemService(ns);
				// Realizo una notificacion por medio de un metodo hecho por mi
    			notificacion(icono_r, "titulo contenido", "texto contenido", "texto extendido");
				// Lanzo la notificacion creada en el paso anterior
    			nm.notify(1, notif);
			}
		});
		return true;
	}

	public void notificacion(int icon, CharSequence textoEstado, CharSequence titulo, CharSequence texto) {
        // Capturo la hora del evento
        long hora = System.currentTimeMillis();
         
        // Definimos la accion de la pulsacion sobre la notificacion (esto es opcional)
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(this, main.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
 
        // Defino la notificacion, icono, texto y hora
        notif = new Notification(icon, textoEstado, hora);
        notif.setLatestEventInfo(context, titulo, texto, contentIntent);
         
        //Defino que la notificacion sea permamente
        notif.flags = Notification.FLAG_ONGOING_EVENT;
    }
}