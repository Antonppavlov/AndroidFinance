package ru.barmaglot.android6.finance.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.barmaglot.andoroid6.finance.core.storage.objects.interfaces.ITreeNode;
import ru.barmaglot.android6.finance.R;


public class TreeNodeAdapter extends RecyclerView.Adapter<TreeNodeAdapter.ViewHolder> {

    private final List<? extends ITreeNode> treeNodeList;

    public TreeNodeAdapter(List<? extends ITreeNode> items) {
        treeNodeList = items;
    }


    //находит xml файл spr_item
    //передает в ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.spr_item, parent, false);

        return new ViewHolder(view);
    }



    /*
    * один раз находится компонент
    *получил на него ссылку в переменную tvSprName
    *ссылка каждый раз будет использоватся чтобы заполнять значение из списка
    * */
    //берем по id определенный элемент из переданного xml
    //присваеваем его статичной переменной
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvSprName;

        public ViewHolder(View view) {
            super(view);
            //ищет элемент среди всех по его id
            //приводит к типу
            //ссылается на него переменной
            tvSprName = (TextView) view.findViewById(R.id.spr_name);
        }
    }




    //выполняется когда заполняется каждое значение для записи
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvSprName.setText(treeNodeList.get(position).getName());
    }


    //возвращает количество записей
    @Override
    public int getItemCount() {
        return treeNodeList.size();
    }



}
