package org.jpat.scuba.core.model.stats;

public class BytecodeStats4All extends ScubaStats
{
    private volatile Integer visitedMethodsAll;
    private volatile Integer analysedBytecodeInstsAll;
    private volatile Integer artifactTargetedCountAll;

    public BytecodeStats4All()
    {
        super();

        this.visitedMethodsAll = Integer.valueOf(0);
        this.analysedBytecodeInstsAll = Integer.valueOf(0);
        this.artifactTargetedCountAll = Integer.valueOf(0);
    }

    private void visitedMethodsSoFarKeeper(final int visitedSoFar)
    {
        this.visitedMethodsAll = Operations4Stats.incrementStatsEntry(this.visitedMethodsAll, visitedSoFar);
    }

    private void analysedInstnsSoFarKeeper(final int analysedInstnsSoFar)
    {
        this.analysedBytecodeInstsAll = Operations4Stats.incrementStatsEntry(this.analysedBytecodeInstsAll, analysedInstnsSoFar);
    }

    public void record4All(final BytecodeStats stats4SingleType)
    {
        this.visitedMethodsSoFarKeeper(stats4SingleType.visitedMehtods4Type());
        this.analysedInstnsSoFarKeeper(stats4SingleType.analysedInstns4Type());
        this.artifactTargetedCount4All(stats4SingleType.targetedArtifactCounted());
    }

    private void artifactTargetedCount4All(final Integer targetedArtifactCounted)
    {
        this.artifactTargetedCountAll = Operations4Stats.incrementStatsEntry(this.artifactTargetedCountAll, targetedArtifactCounted);
    }

    public int getVisitedMethodsStats()
    {
        return this.visitedMethodsAll;
    }

    public int getAnalysedBytecodeInstns()
    {
        return this.analysedBytecodeInstsAll;
    }

    public int getArtifactTargetedStats()
    {
        return this.artifactTargetedCountAll;
    }
}