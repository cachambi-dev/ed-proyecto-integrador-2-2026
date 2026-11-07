package structures;

//
// Created by Julio Tentor <jtentor@fi.unju.edu.ar>
//

/** Implementa un árbol binario y operaciones de recorrido y conteo. */
public class BinaryTree<ELEMENT> {

    //region Binary Tree Node Class

    protected class BTNode<T> {

        public T item;
        public BTNode<T> left;
        public BTNode<T> right;

        public BTNode() {
            this(null, null, null);
        }

        public BTNode(T item) {
            this(item, null, null);
        }

        public BTNode(T item, BTNode<T> left, BTNode<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.item.toString();
        }

        // Método para propósitos académicos
        public void Visit() {
            System.out.printf("%s ", this.item.toString());
        }
    }
    //endregion

    //region Attributes

    protected BTNode<ELEMENT> root;

    //endregion

    //region Constructors

    /** Crea un árbol vacío. */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * Crea un árbol con un único elemento como raíz.
     *
     * @param item elemento de la raíz
     */
    public BinaryTree(ELEMENT item) {
        this(item, null, null);
    }

    /**
     * Crea un árbol con raíz y subárboles izquierdo y derecho opcionales.
     *
     * @param item elemento de la raíz
     * @param left subárbol izquierdo, o {@code null}
     * @param right subárbol derecho, o {@code null}
     */
    public BinaryTree(ELEMENT item, BinaryTree<ELEMENT> left, BinaryTree<ELEMENT> right) {
        this.root = new BTNode<ELEMENT>(item, null, null);
        if (left != null) {
            this.root.left = left.root;
        }
        if (right != null) {
            this.root.right = right.root;
        }
    }

    //endregion

    /**
     * Devuelve una representación del árbol usando paréntesis para sus hijos.
     *
     * @return representación textual del árbol
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, this.root);
        return sb.toString();
    }

    protected void toString(StringBuilder sb, BTNode<ELEMENT> root) {
        if (root != null) {
            sb.append(root.item.toString());
            if (root.left != null) {
                sb.append("(");
                toString(sb, root.left);
                if (root.right != null) {
                    sb.append(",");
                    toString(sb, root.right);
                }
                sb.append(")");
            } else {
                if (root.right != null) {
                    sb.append("(,");
                    toString(sb, root.right);
                    sb.append(")");
                }
            }
        }
    }

    /** Recorre el árbol en orden raíz, izquierdo y derecho e imprime sus elementos. */
    public void PreOrder() {
        PreOrder(this.root);
    }

    protected void PreOrder(BTNode<ELEMENT> root) {
        if (root != null) {
            root.Visit();
            PreOrder(root.left);
            PreOrder(root.right);
        }
    }

    /** Recorre el árbol en orden izquierdo, raíz y derecho e imprime sus elementos. */
    public void InOrder() {
        InOrder(this.root);
    }

    protected void InOrder(BTNode<ELEMENT> root) {
        if (root != null) {
            InOrder(root.left);
            root.Visit();
            InOrder(root.right);
        }
    }

    /** Recorre el árbol en orden izquierdo, derecho y raíz e imprime sus elementos. */
    public void PostOrder() {
        PostOrder(this.root);
    }

    protected void PostOrder(BTNode<ELEMENT> root) {
        if (root != null) {
            PostOrder(root.left);
            PostOrder(root.right);
            root.Visit();
        }
    }

    /** Recorre el árbol en orden derecho, raíz e izquierdo e imprime sus elementos. */
    public void DescendingOrder() {
        DescendingOrder(this.root);
    }

    protected void DescendingOrder(BTNode<ELEMENT> root) {
        if (root != null) {
            DescendingOrder(root.right);
            root.Visit();
            DescendingOrder(root.left);
        }
    }

    /**
     * Devuelve la cantidad total de nodos del árbol.
     *
     * @return cantidad de nodos
     */
    public int NodeCount() {
        return NodeCount(this.root);
    }

    protected int NodeCount(BTNode<ELEMENT> root) {
        if (root != null) {
            return 1 + NodeCount(root.left) + NodeCount(root.right);
        }
        return 0;
    }

    /**
     * Devuelve la cantidad de hojas del árbol.
     *
     * @return cantidad de hojas
     */
    public int LeafCount() {
        return LeafCount(this.root);
    }

    protected int LeafCount(BTNode<ELEMENT> root) {
        if (root != null) {
            if ((root.left == null) && (root.right == null)) {
                return 1;
            } else {
                return LeafCount(root.left) + LeafCount(root.right);
            }
        }
        return 0;
    }

    /**
     * Devuelve la cantidad de nodos internos del árbol.
     *
     * @return cantidad de nodos internos
     */
    public int InternalCount() {
        return InternalCount(this.root);
    }

    protected int InternalCount(BTNode<ELEMENT> root) {
        if (root != null) {
            if ((root.left == null) && (root.right == null)) {
                return 0;
            } else {
                return 1 + InternalCount(root.left) + InternalCount(root.right);
            }
        }
        return 0;
    }

    /**
     * Devuelve el nivel máximo, considerando la raíz como nivel cero.
     *
     * @return nivel máximo, o {@code -1} si el árbol está vacío
     */
    public int MaxLevel() {
        return MaxLevel(this.root);
    }

    protected int MaxLevel(BTNode<ELEMENT> root) {
        if (root != null) {
            if ((root.left != null) || (root.right != null)) {
                int leftLevel = MaxLevel(root.left);
                int rightLevel = MaxLevel(root.right);
                return 1 + Math.max(leftLevel, rightLevel);
            }
            return 0;
        }
        return -1;
    }

    /**
     * Devuelve la altura del árbol en cantidad de niveles.
     *
     * @return altura del árbol
     */
    public int Height() {
        return MaxLevel() + 1;
    }
}
