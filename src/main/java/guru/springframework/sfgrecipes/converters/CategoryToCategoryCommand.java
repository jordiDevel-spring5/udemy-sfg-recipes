package guru.springframework.sfgrecipes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.sfgrecipes.commands.CategoryCommand;
import guru.springframework.sfgrecipes.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Override
	public CategoryCommand convert(Category source) {
		if (source == null) {
			return null;
		}
		
		final CategoryCommand categoryCmd = new CategoryCommand();
		categoryCmd.setId(source.getId());
		categoryCmd.setDescription(source.getDescription());
		return categoryCmd;
	}

}
