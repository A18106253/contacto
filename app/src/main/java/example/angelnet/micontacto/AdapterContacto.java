package example.angelnet.micontacto;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.contactos.movil.computacion.uss.contactos.Modelo.Contacto;
        import com.contactos.movil.computacion.uss.contactos.R;

        import java.util.List;

/**
 * Created by guima on 27/11/2015.
 */
public class AdapterContacto extends BaseAdapter {

    private List<Contacto> list;
    private Activity activity;


    public AdapterContacto(Activity activity, List<Contacto> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.detalle_contacto, null);

        }
        Contacto movimiento = list.get(position);
        TextView tvNombreV = (TextView) v.findViewById(R.id.tvNombreV);
        tvNombreV.setText(movimiento.getNombre());
        TextView tvNumeroV = (TextView) v.findViewById(R.id.tvNumeroV);
        tvNumeroV.setText(movimiento.getTelefono());
        TextView tvEmailV = (TextView) v.findViewById(R.id.tvEmailV);
        tvEmailV.setText(movimiento.getEmail());
        return v;
    }
}