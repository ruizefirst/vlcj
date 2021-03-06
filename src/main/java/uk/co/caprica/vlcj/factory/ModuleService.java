package uk.co.caprica.vlcj.factory;

import uk.co.caprica.vlcj.binding.internal.libvlc_module_description_t;
import uk.co.caprica.vlcj.player.ModuleDescription;

import java.util.ArrayList;
import java.util.List;

public final class ModuleService extends BaseService {

    ModuleService(MediaPlayerFactory factory) {
        super(factory);
    }

    /**
     * Get the available audio filters.
     *
     * @return collection of audio filter descriptions
     */
    public List<ModuleDescription> audioFilters() {
        libvlc_module_description_t moduleDescriptions = libvlc.libvlc_audio_filter_list_get(instance);
        List<ModuleDescription> result = getModuleDescriptions(moduleDescriptions);
        libvlc.libvlc_module_description_list_release(moduleDescriptions);
        return result;
    }

    /**
     * Get the available video filters.
     *
     * @return collection of video filter descriptions
     */
    public List<ModuleDescription> videoFilters() {
        libvlc_module_description_t moduleDescriptions = libvlc.libvlc_video_filter_list_get(instance);
        List<ModuleDescription> result = getModuleDescriptions(moduleDescriptions);
        libvlc.libvlc_module_description_list_release(moduleDescriptions);
        return result;
    }

    /**
     * Convert a collection of native module description structures.
     *
     * @param moduleDescriptions module descriptions
     * @return collection of module descriptions
     */
    private List<ModuleDescription> getModuleDescriptions(libvlc_module_description_t moduleDescriptions) {
        List<ModuleDescription> result = new ArrayList<ModuleDescription>();
        libvlc_module_description_t moduleDescription = moduleDescriptions;
        while(moduleDescription != null) {
            result.add(new ModuleDescription(moduleDescription.psz_name, moduleDescription.psz_shortname, moduleDescription.psz_longname, moduleDescription.psz_help));
            moduleDescription = moduleDescription.p_next;
        }
        return result;
    }

}
