package cu.tissca.x901.wad.model;

import cu.tissca.x901.wad.Visitor;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ParamDescriptor extends AbstractDescriptor {
    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitParamDescriptor(this);
        } finally {
            visitor.endVisitParamDescriptor(this);
        }
    }
}
