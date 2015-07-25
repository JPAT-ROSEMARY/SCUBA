package org.jpat.scuba.core.model.stats;

public final class BytecodeStats extends ScubaStats
{
    private volatile Integer analysedTypes;
    private volatile Integer visitedMethods;
    private volatile Integer analysedBytecodeInst;
    private final BytecodeTargetedStats stats4SingleTypeTargeted;

    public BytecodeStats(final BytecodeTargetedStats bytecodeTargetedStats)
    {
        super();

        assert null != bytecodeTargetedStats : "Parameter 'bytecodeTargetedStats' of 'BytecodeStats''s ctor must not be null";

        this.stats4SingleTypeTargeted = bytecodeTargetedStats;
        this.analysedTypes = Integer.valueOf(0);
        this.visitedMethods = Integer.valueOf(0);
        this.analysedBytecodeInst = Integer.valueOf(0);
    }

    public void incrementVisitedMethodsCount()
    {
        this.visitedMethods = Operations4Stats.incrementStatsEntry(this.visitedMethods);
    }

    public void incrementAnalysedBytecodeInst()
    {
        this.analysedBytecodeInst = Operations4Stats.incrementStatsEntry(this.analysedBytecodeInst);
    }

    public void incrementTargetedArtifact(final int opcode)
    {
        this.stats4SingleTypeTargeted.incrementTargetedArtifact(opcode);
    }

    public Integer visitedMehtods4Type()
    {
        return this.visitedMethods;
    }

    public Integer analysedInstns4Type()
    {
        return this.analysedBytecodeInst;
    }

    public Integer targetedArtifactCounted()
    {
        return this.stats4SingleTypeTargeted.targetedArtifactCount();
    }

    public void incrementAnalysedTypesCounter()
    {
        this.analysedTypes = Operations4Stats.incrementStatsEntry(this.analysedTypes);
    }

    @Override
    public String toString()
    {
        return "Contains Stats on bytecode for a single type analysed";
    }
}
