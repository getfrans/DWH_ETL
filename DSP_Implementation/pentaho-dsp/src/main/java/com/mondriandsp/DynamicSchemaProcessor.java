package com.mondriandsp;

import mondrian.olap.Util;
import mondrian.spi.impl.FilterDynamicSchemaProcessor;

import org.pentaho.platform.api.engine.IPentahoSession;
import org.pentaho.platform.engine.core.system.PentahoSessionHolder;

import java.io.InputStream;


/**
 * Created by Francis 07-09-2018.
 */
public class DynamicSchemaProcessor extends FilterDynamicSchemaProcessor {

    @Override
    protected String filter(final String schemaUrl, final Util.PropertyList connectInfo, final InputStream stream)
            throws java.lang.Exception {
        String originalSchema = super.filter(schemaUrl, connectInfo, stream);
        IPentahoSession session = PentahoSessionHolder.getSession();
        String userName = session.getName().toString();
        String modifiedSchema = originalSchema.replace("%PLACE_HOLDER%"," IN (SELECT BranchId FROM dim_biuseraccount WHERE LoginUserName = '"+userName+"')");
        return modifiedSchema;
    }

}
