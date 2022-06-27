package br.edu.ifsuldeminas.mch.tarefas2;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.List;

import br.edu.ifsuldeminas.mch.tarefas2.databinding.FragmentCategoryBinding;
import br.edu.ifsuldeminas.mch.tarefas2.databinding.FragmentMainCategoryBinding;
import br.edu.ifsuldeminas.mch.tarefas2.db.CategoryDAO;
import br.edu.ifsuldeminas.mch.tarefas2.domain.Category;

public class CategoryFragment extends Fragment{

    private Category category;
    private FragmentCategoryBinding binding;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false );
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSaveCategoryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText descriptionTIET = binding.categoryDescription;
                String description = descriptionTIET.getText().toString();
                description = description != null ? description : "";

                if (description.equals("")){
                    Toast.makeText(getContext(),R.string.category_description_empty, Toast.LENGTH_SHORT).show();
                }else {
                    if (category == null) {
                        Category category = new Category(0, description);

                        CategoryDAO dao = new CategoryDAO(getContext());
                        dao.save(category);

                        Toast.makeText(getContext(), R.string.category_saved, Toast.LENGTH_SHORT).show();
                    }else {
                        category.setName(description);

                        CategoryDAO dao = new CategoryDAO(getContext());
                        dao.update(category);
                        Toast.makeText(getContext(), R.string.task_updated, Toast.LENGTH_SHORT).show();
                    }
                }

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_CategoryFragment_to_MainCategoryFragment);
                navController.popBackStack(R.id.CategoryFragment, true);
            }});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        Object taskArguments = null;
        if (getArguments() != null){
            taskArguments = getArguments().getSerializable("category");
        }
        if (taskArguments != null){
            category = (Category) taskArguments;
            TextInputEditText categoryDescription = binding.categoryDescription;
            categoryDescription.setText(category.getName());
        }
    }


}