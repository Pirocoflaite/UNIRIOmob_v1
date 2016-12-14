package unirio.uniriomob.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import unirio.uniriomob.R;
import unirio.uniriomob.other.NewsCustomAdapter;
import unirio.uniriomob.other.RestaurantDishCustomAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    private TabHost tabHost;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        tabHost = (TabHost) view.findViewById(R.id.news_tabhost);
        tabHost.setup();

        //Set Today's Dishes Tab
        TabHost.TabSpec uniriobrSpec = tabHost.newTabSpec("news_tabhost_uniriobr");
        uniriobrSpec.setContent(R.id.news_tabhost_uniriobr);
        uniriobrSpec.setIndicator("Unirio.br");

        TabHost.TabSpec facebookSpec = tabHost.newTabSpec("news_tabhost_fb");
        facebookSpec.setContent(R.id.news_tabhost_fb);
        facebookSpec.setIndicator("Facebook");

        tabHost.addTab(uniriobrSpec);
        tabHost.addTab(facebookSpec);

        //Generate dishes list
        ArrayList<String> uniriobrNewsList = new ArrayList<String>();
        uniriobrNewsList.add("Discentes do Programa de Pós-Graduação em Direito e Políticas Públicas promovem seminário");
        uniriobrNewsList.add("Questão de gênero em debate na série ‘Mesas-redondas Convergências & Divergências’");
        uniriobrNewsList.add("‘Nutrição e destoxificação’ é tema do ciclo de palestras Alimentação e Saúde");
        uniriobrNewsList.add("Biblioteca da UNIRIO promove lançamento de livro infantil");
        uniriobrNewsList.add("‘Campus das Artes’ promove apresentações culturais na UNIRIO");
        uniriobrNewsList.add("14ª Semana de Integração Acadêmica termina com entrega de premiações");
        uniriobrNewsList.add("Simpósio no IB promove reflexões sobre saúde e espiritualidade");

        ArrayList<String> facebookNewsList = new ArrayList<String>();
        facebookNewsList.add("Docente do IVL lança livro sobre canções eruditas no Brasil e nos Estados Unidos");
        facebookNewsList.add("‘Disbiose, o que é? Como tratar e prevenir?’ será tema de palestra do PPGAN");
        facebookNewsList.add("Progepe promove cursos de capacitação para servidores da UNIRIO");
        facebookNewsList.add("Grupo Geotales promove sarau  sobre Geologia e Paleontologia");
        facebookNewsList.add("II Fórum dos Cursos de Graduação debate perspectivas democráticas do ensino superior");

        NewsCustomAdapter uniriobrAdapter = new NewsCustomAdapter(uniriobrNewsList, view.getContext());
        ListView uniriobrLView = (ListView)view.findViewById(R.id.list_news_uniriobr);
        uniriobrLView.setAdapter(uniriobrAdapter);

        NewsCustomAdapter facebookAdapter = new NewsCustomAdapter(facebookNewsList, view.getContext());
        ListView facebookLView = (ListView)view.findViewById(R.id.list_news_fb);
        facebookLView.setAdapter(facebookAdapter);

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
