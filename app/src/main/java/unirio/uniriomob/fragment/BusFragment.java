package unirio.uniriomob.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import unirio.uniriomob.R;
import unirio.uniriomob.other.BusPlaceCustomAdapter;
import unirio.uniriomob.other.NewsCustomAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusFragment extends Fragment {

    private TabHost tabHost;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusFragment newInstance(String param1, String param2) {
        BusFragment fragment = new BusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bus, container, false);

        tabHost = (TabHost) view.findViewById(R.id.bus_tabhost);
        tabHost.setup();

        ((TextView) view.findViewById(R.id.txt_bus_route1_status))
                .setText(getResources().getText(R.string.nav_bus_txt_status) + ": Normal");

        ((TextView) view.findViewById(R.id.txt_bus_route1_passengers))
                .setText(getResources().getText(R.string.nav_bus_txt_passengers) + ": 25");

        ((TextView) view.findViewById(R.id.txt_bus_route1_time))
                .setText(getResources().getText(R.string.nav_bus_txt_time) + ": 22:20");

        ((TextView) view.findViewById(R.id.txt_bus_route1_local))
                .setText(getResources().getText(R.string.nav_bus_txt_place) + ": Av. Pasteur, 458");

        ((Button) view.findViewById(R.id.btn_bus_route1_confirm))
                .setText(getResources().getText(R.string.nav_bus_btn_confirm));

        ((TextView) view.findViewById(R.id.txt_bus_route2_status))
                .setText(getResources().getText(R.string.nav_bus_txt_status) + ": Normal");

        ((TextView) view.findViewById(R.id.txt_bus_route2_passengers))
                .setText(getResources().getText(R.string.nav_bus_txt_passengers) + ": 17");

        ((TextView) view.findViewById(R.id.txt_bus_route2_time))
                .setText(getResources().getText(R.string.nav_bus_txt_time) + ": 22:20");

        ((TextView) view.findViewById(R.id.txt_bus_route2_local))
                .setText(getResources().getText(R.string.nav_bus_txt_place) + ": Av. Pasteur, 458");

        ((Button) view.findViewById(R.id.btn_bus_route2_confirm))
                .setText(getResources().getText(R.string.nav_bus_btn_confirm));

        //Set Today's Dishes Tab
        TabHost.TabSpec route1Spec = tabHost.newTabSpec("bus_tabhost_route_1");
        route1Spec.setContent(R.id.bus_tabhost_route_1);
        route1Spec.setIndicator("Baixada Fluminense");

        TabHost.TabSpec route2Spec = tabHost.newTabSpec("bus_tabhost_route_2");
        route2Spec.setContent(R.id.bus_tabhost_route_2);
        route2Spec.setIndicator("Zona Oeste");

        tabHost.addTab(route1Spec);
        tabHost.addTab(route2Spec);

        //Generate dishes list
        ArrayList<String> route1BusList = new ArrayList<String>();
        route1BusList.add("Praia de Botafogo (primeiro ponto de ônibus)");
        route1BusList.add("Avenida Brasil (Porcão)");
        route1BusList.add("Ricardo de Albuquerque (ponto principal)");
        route1BusList.add("Anchieta (ponto principal)");
        route1BusList.add("Olinda (Praça de Olinda)");
        route1BusList.add("Nilópolis (Estação de Nilópolis)");
        route1BusList.add("Edson Passos (Estação Edson Pastos)");
        route1BusList.add("Mesquita (Estação Mesquita)");
        route1BusList.add("Nova Iguaçu (Centro)");

        ArrayList<String> route2BusList = new ArrayList<String>();
        route2BusList.add("Deodoro (Praça de Deodoro)");
        route2BusList.add("Magalhães Bastos (Ponto principal)");
        route2BusList.add("Realengo (Praça Canhão)");
        route2BusList.add("Padre Miguel (Estação de Padre Miguel)");
        route2BusList.add("Bangu (Estação Bangu)");
        route2BusList.add("Senador Camará (Estação Senador Camará)");
        route2BusList.add("Campo Grande (Centro)");

        BusPlaceCustomAdapter route1Adapter = new BusPlaceCustomAdapter(route2BusList, view.getContext());
        ListView route1LView = (ListView) view.findViewById(R.id.list_bus_route_1);
        route1LView.setAdapter(route1Adapter);

        BusPlaceCustomAdapter route2Adapter = new BusPlaceCustomAdapter(route1BusList, view.getContext());
        ListView route2LView = (ListView) view.findViewById(R.id.list_bus_route_2);
        route2LView.setAdapter(route2Adapter);

        // Inflate the layout for this fragment
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
