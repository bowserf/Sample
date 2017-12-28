# Dagger2 explanations and others :

## @interface :

The @ symbol denotes an annotation type definition.
That means it is not really an interface, but rather a new annotation type -- to be used as a
function modifier, such as @override.


## Dependency VS Subcomponent:

Component Dependencies - Use this when:
    - you want to keep two components independent.
    - you want to explicitly show what dependencies from one component is used by the other

In the new component, just add in @Component, a field dependency={DependencyComponent.class}
The child component can't access to Objects which have not a provide methods define in the parent
Component.

Subcomponents - Use this when:
    - you want to keep two component cohesive
    - you may not care to explicitly show what dependencies from one component is used by the other

In the new component, put the module used @Subcomponent(modules = HomeModule.class).
In the parent component (not in the module), define a provide method which return the child
component and take the module in parameter.
The child component has access to the whole graph. It means to provide Objects whose there is no
provide method in the Component (but one in the module). OtherComponent is a SubComponent of
TestDagger2ApplicationComponent and the module OtherModule can access to the Context define in
TestDagger2Module.

https://stackoverflow.com/questions/29587130/dagger-2-subcomponents-vs-component-dependencies


## @Component.Builder

A builder for a component. Components may have a single nested static abstract class or interface
annotated with @Component.Builder. If they do, then the component's generated builder will match
the API in the type.

To put in the component :
@Component.Builder
   interface Builder {
        AppComponent build();
        Builder appModule(AppModule appModule);
    }

It does the same things than traditional way. But with @BindsInstance, we can customize this
builder.

## @BindsInstance

Use to provide constructor arguments which are immediately send by a provide method.

Previously, the TestDagger2Module looked like this :
@Module
public class TestDagger2Module {

    private Context context;

    public TestDagger2Module(Context context){
        this.context = context;
    }

    @Provides
    public Context provideApplicationContext(){
        return context;
    }

}

But if we add this in the builder:
@BindsInstance Builder provideContext(Context context);
We can remove the whole code of the module.
If we had another provide method which takes a Context in parameter, we had only keep this method.

Now we don't need to instanciate a module when constructing the graph, just provide to the builder a
context with provideContext(Context) method.


## @Reusable

Define like this.
@Reusable
public class SomeObject {

    @Inject
    public SomeObject() {
    }
}

It allows to say we would like to keep only one instance of SomeObject but if memory is too low, we
can destroy the object. So it's possible to have different instances of SomeObject during the life
time of the Component.

## @Binds

Replace
@Provides
public LoginContract.Presenter
  provideLoginPresenter(LoginPresenter loginPresenter) {
    return loginPresenter;
}
by
@Binds
public abstract LoginContract.Presenter
  provideLoginPresenter(LoginPresenter loginPresenter); and make the module abstract.
By settings module abstract, Dagger won't instantiate the module but directly use the Provider of
the injected parameter (LoginPresenter above)
If there are both methods Provide and Bind in the module, we can set static Provide methods to do
the same.

## Scope on provide method or injected constructor :

Need to define this with the same scope as define in the constructor to be able to only have one
reference for the object during the life of the component.