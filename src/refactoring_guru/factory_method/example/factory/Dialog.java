package refactoring_guru.factory_method.example.factory;

import refactoring_guru.factory_method.example.buttons.Button;

/**
 * EN: Base factory class. Note that "factory" is merely a role for the class.
 * It should have some core business logic which needs different products to be
 * created.
 * <p>
 * RU: Базовый класс фабрики. Заметьте, что "фабрика" – это всего лишь
 * дополнительная роль для класса. Он уже имеет какую-то бизнес-логику, в
 * которой требуется создание разнообразных продуктов.
 */
public abstract class Dialog {

  public void renderWindow() {
    Button okButton = createButton();
    okButton.render();
  }

  /**
   * EN: Subclasses will override this method in order to create specific
   * button objects.
   * <p>
   * RU: Подклассы будут переопределять этот метод, чтобы создавать конкретные
   * объекты продуктов, разные для каждой фабрики.
   */
  public abstract Button createButton();
}
