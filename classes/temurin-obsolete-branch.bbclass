# Warn once per build that this branch of meta-openjdk-temurin is obsolete.
# Anonymous python is not allowed in layer.conf, so this is a global event
# handler pulled in via INHERIT.
addhandler temurin_obsolete_branch_warn
temurin_obsolete_branch_warn[eventmask] = "bb.event.BuildStarted"
python temurin_obsolete_branch_warn() {
    bb.warn("meta-openjdk-temurin: the 'dunfell' branch is obsolete and unmaintained. "
            "Only critical security fixes may still be backported. "
            "Migrate to kirkstone, scarthgap, or main.")
}
