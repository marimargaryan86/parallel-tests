namespace ParallelTests;

public class Tests
{
    [SetUp]
    public void Setup()
    {
    }

    [Test]
    public void WriteArtifactPerBatch()
    {
        var fileName = "my-filename-" + TestContext.CurrentContext.Test.ID + ".txt";
        File.WriteAllText(fileName, $"This file was created in: {TestContext.CurrentContext.Test.Name}");
        Assert.True(File.Exists(fileName));
    }
    [Test]
    public void SumTest()
    {
        Assert.AreEqual(6, 3 + 3);
    }
    
    [Test]
    public void MultiplyTest()
    {
        Assert.AreEqual(9, 3 * 3);
    }
}

public class Testsik
{
    [SetUp]
    public void Setup()
    {
    }

    [Test]
    public void SumTestik()
    {
        Assert.AreEqual(6, 3 + 3);
    }
    
    [Test]
    public void MultiplyTestik()
    {
        Assert.AreEqual(9, 3 * 3);
    }
}
