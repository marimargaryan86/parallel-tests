namespace ParallelTests;

public class Tests
{
    [SetUp]
    public void Setup()
    {
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
    [Test]
    public void MultiplyTest_Commit_1()
    {
        Assert.AreEqual(9, 3 * 3);
    }
     [Test]
    public void MultiplyTest_Commit_2()
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
