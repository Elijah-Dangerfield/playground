package com.dangerfield.hiltplayground.util.mtadapter;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.dangerfield.hiltplayground.util.mtadapter.interfaces.BiConsumer;
import com.dangerfield.hiltplayground.util.mtadapter.interfaces.Consumer;
import com.dangerfield.hiltplayground.util.mtadapter.interfaces.Function1;
import com.dangerfield.hiltplayground.util.mtadapter.interfaces.Supplier;
import com.dangerfield.hiltplayground.util.mtadapter.interfaces.TriConsumer;

import java.util.List;

/**
 * This Factory is to allow a more functional approach to creating TypeAdapters.
 * Contains methods for creating TypeAdapters composed of methods.
 */
public class TypeAdapterFactory {

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder) {
        return create(targetClass,
                onCreateViewHolder,
                toTriConsumer(null),
                null,
                null,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder) {
        return create(isMyData,
                onCreateViewHolder,
                toTriConsumer(null),
                null,
                null,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder) {
        return create(targetClass,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                null,
                null,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder) {
        return create(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                null,
                null,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder) {
        return create(isMyData,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                null,
                null,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder) {
        return create(isMyData,
                onCreateViewHolder,
                onBindViewHolder,
                null,
                null,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach) {
        return create(targetClass,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                onAttach,
                onDetach,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach) {
        return create(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach) {
        return create(isMyData,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                onAttach,
                onDetach,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach) {
        return create(isMyData,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                null);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return create(targetClass,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                null,
                null,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return create(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                null,
                null,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return create(isMyData,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                null,
                null,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return create(isMyData,
                onCreateViewHolder,
                onBindViewHolder,
                null,
                null,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                onAttach,
                onDetach,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(isMyData,
                onCreateViewHolder,
                toTriConsumer(onBindViewHolder),
                onAttach,
                onDetach,
                spanSupplier);
    }

    @NonNull
    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  @Nullable final Consumer<? super V> onAttach,
                                                                                  @Nullable final Consumer<? super V> onDetach,
                                                                                  @Nullable final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(isMyData,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                spanSupplier);
    }

    static <V, D> TriConsumer<V, D, List<Object>> toTriConsumer(@Nullable final BiConsumer<? super V, ? super D> biConsumer) {
        return new TriConsumer<V, D, List<Object>>() {
            @Override
            public void invoke(final V v, final D d, final List<Object> objects) {
                if (biConsumer != null) {
                    biConsumer.invoke(v, d);
                }
            }
        };
    }
}
